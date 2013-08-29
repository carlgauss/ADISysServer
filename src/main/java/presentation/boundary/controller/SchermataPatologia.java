package presentation.boundary.controller;

import business.entity.Patologia;
import business.transfer.PatologiaTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.component.ChoiceBoxGravita;
import presentation.boundary.controller.component.TranslatedLabel;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.MessageDisplayer;
import utility.Parameter;

import java.net.URL;
import java.util.*;

public class SchermataPatologia extends Schermata {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private TableView<Patologia> patologia;
    @FXML
    private TableColumn<Patologia, String> codice;
    @FXML
    private TableColumn<Patologia, String> nome;
    @FXML
    private TableColumn<Patologia, Integer> gravita;
    @FXML
    private TextField inputCodice;

    private static final int DEFAULT_GRAVITA = 1;

    @FXML
    private void onAggiungi(ActionEvent event) {
        update();

        Parameter codiceParameter = new Parameter();

        codiceParameter.setValue("codice", inputCodice.getText());
        codiceParameter.setValue("patologieDaInserire", editedPatologiaMap.keySet());

        Object result = fc.processRequest("VerificaCodicePatologia", codiceParameter);

        if (result != null) {
            PatologiaTO patologiaTO = new PatologiaTO();
            patologiaTO.setCodice(inputCodice.getText());
            patologiaTO.setGravita(DEFAULT_GRAVITA);

            patologiaTO.setToInsert(true);

            patologiaData.add(patologiaTO);
            editedPatologiaMap.put(patologiaTO.getCodice(), patologiaTO);
        }
    }

    @FXML
    private void onOk(ActionEvent event) {
        update();

        Object result = null;

        Parameter diseaseParameter = new Parameter();

        diseaseParameter.setValue("listaPatologia", editedPatologiaMap.values());

        result = fc.processRequest("AggiornaTutteLePatologie", diseaseParameter);

        if (result != null) {
            MessageDisplayer.showAcceptMessage(null, "updatedDiseases");

            getStage().setResult(result);
            getStage().close();
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        getStage().close();
    }

    private Map<String, PatologiaTO> editedPatologiaMapCache = new HashMap<>();
    private Map<String, PatologiaTO> editedPatologiaMap = new HashMap<>();
    private volatile ObservableList<Patologia> patologiaData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        patologia.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        List<Patologia> patologiaList = (List<Patologia>) fc.processRequest("VisualizzaTuttePatologie", null);
        patologiaData = FXCollections.observableArrayList(patologiaList);

        codice.setCellValueFactory(new PropertyValueFactory<Patologia, String>("codice"));

        nome.setCellValueFactory(new PropertyValueFactory<Patologia, String>("nome"));
        nome.setCellFactory(new EditingCellFactory());

        gravita.setCellValueFactory(new PropertyValueFactory<Patologia, Integer>("gravita"));
        gravita.setCellFactory(new ChoiceCellFactory());

        patologia.setItems(patologiaData);

        patologia.setPlaceholder(new TranslatedLabel("noDisease"));
    }

    private static final String BLANK = "";

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private void update() {
        Set<Map.Entry<String, PatologiaTO>> entrySet = editedPatologiaMapCache.entrySet();

        Iterator<Map.Entry<String, PatologiaTO>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, PatologiaTO> entry = iterator.next();
            PatologiaTO patologiaTO = entry.getValue();
            patologiaData.set(patologiaTO.getIndex(), patologiaTO);
            editedPatologiaMap.put(entry.getKey(), patologiaTO);

            iterator.remove();
        }
    }

    private class EditingCellFactory implements Callback<TableColumn<Patologia, String>, TableCell<Patologia, String>> {
        @Override
        public TableCell<Patologia, String> call(TableColumn<Patologia, String> patologiaStringTableColumn) {
            return new TableCell<Patologia, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);

                    if (!empty) {
                        final int index = getIndex();

                        final TextField textField = new TextField();

                        Patologia oldPatologia = patologia.getItems().get(index);

                        final Patologia finalOldPatologia = oldPatologia;

                        textField.setText(text);
                        textField.getStyleClass().add("transparent");

                        textField.textProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observableValue, String oltText, String newText) {
                                Patologia oldPatologia = finalOldPatologia;

                                if (editedPatologiaMapCache.containsKey(oldPatologia.getCodice())) {
                                    oldPatologia = editedPatologiaMapCache.get(oldPatologia.getCodice());
                                }

                                PatologiaTO editedPatologia = new PatologiaTO(oldPatologia);

                                if (finalOldPatologia instanceof PatologiaTO) {
                                    editedPatologia.setToInsert(((PatologiaTO) finalOldPatologia).isToInsert());
                                }

                                editedPatologia.setNome(newText);
                                editedPatologia.setIndex(index);
                                editedPatologiaMapCache.put(editedPatologia.getCodice(), editedPatologia);
                            }
                        });

                        setGraphic(textField);
                    } else {
                        setItem(null);
                        setGraphic(null);
                    }
                }
            };
        }
    } //END CLASS

    private class ChoiceCellFactory implements Callback<TableColumn<Patologia, Integer>, TableCell<Patologia, Integer>> {
        @Override
        public TableCell<Patologia, Integer> call(TableColumn<Patologia, Integer> patologiaIntegerTableColumn) {
            return new TableCell<Patologia, Integer>() {
                @Override
                protected void updateItem(Integer integer, boolean empty) {
                    super.updateItem(integer, empty);
                    if (!empty) {
                        final int index = getIndex();

                        final ChoiceBoxGravita boxGravita = new ChoiceBoxGravita();

                        Patologia oldPatologia = patologia.getItems().get(index);

                        if (editedPatologiaMapCache.containsKey(oldPatologia.getCodice())) {
                            oldPatologia = editedPatologiaMapCache.get(oldPatologia.getCodice());
                        }

                        final Patologia finalOldPatologia = oldPatologia;

                        boxGravita.setValue(integer);
                        setAlignment(Pos.CENTER);

                        boxGravita.valueProperty().addListener(new ChangeListener<Integer>() {
                            @Override
                            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldGravita, Integer newGravita) {
                                Patologia oldPatologia = finalOldPatologia;

                                if (editedPatologiaMapCache.containsKey(oldPatologia.getCodice())) {
                                    oldPatologia = editedPatologiaMapCache.get(oldPatologia.getCodice());
                                }

                                PatologiaTO editedPatologia = new PatologiaTO(oldPatologia);

                                if (finalOldPatologia instanceof PatologiaTO) {
                                    editedPatologia.setToInsert(((PatologiaTO) finalOldPatologia).isToInsert());
                                }

                                editedPatologia.setGravita(newGravita);
                                editedPatologia.setIndex(index);
                                editedPatologiaMapCache.put(editedPatologia.getCodice(), editedPatologia);
                            }
                        });

                        setGraphic(boxGravita);

                    } else {
                        setItem(null);
                        setGraphic(null);
                    }
                }
            };
        }
    } //END CLASS
}
