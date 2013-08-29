package presentation.boundary.controller;

import business.entity.Patologia;
import business.entity.Paziente;
import business.transfer.BooleanBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.component.IndexedTextField;
import presentation.boundary.controller.itemfactory.BooleanCheckTableFactory;
import presentation.boundary.controller.itemfactory.PatologiaGravitaDepictionTableFactory;
import presentation.boundary.controller.itemfactory.PatologiaPartialDepictionTableFactory;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.*;

import java.net.URL;
import java.util.*;

public class SchermataImmissionePaziente extends SchermataImmissione {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private Labeled idLbl;
    @FXML
    private Labeled id;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField data;
    @FXML
    private ListView<String> numero;

    @FXML
    private TableView<BooleanBox<Patologia>> patologia;
    @FXML
    private TableColumn<BooleanBox<Patologia>, BooleanProperty> checkedPatologia;
    @FXML
    private TableColumn<BooleanBox<Patologia>, Patologia> patologiaColonna;
    @FXML
    private TableColumn<BooleanBox<Patologia>, Patologia> gravitaPatologia;

    @FXML
    private void onOk(ActionEvent event) {
        updateAll();
        Object result = null;

        Parameter patientParameter = new Parameter();

        patientParameter.setValue("id", id.getText());
        patientParameter.setValue("nome", nome.getText());
        patientParameter.setValue("cognome", cognome.getText());
        patientParameter.setValue("data", data.getText());
        patientParameter.setValue("numero", numero.getItems());

        List<Patologia> patologiaList = new ArrayList<>();

        for (BooleanBox<Patologia> patologiaBox : patologiaData) {
            if(patologiaBox.getChecked().get()) {
                patologiaList.add(patologiaBox.getElement());
            }
        }

        patientParameter.setValue("patologia", patologiaList);

        if (isEdit) {
            result = fc.processRequest("ModificaPaziente", patientParameter);
        } else {
            result = fc.processRequest("InserisciPaziente", patientParameter);
        }

        if (result != null) {
            if (isEdit) {
                MessageDisplayer.showAcceptMessage(null, "editedPatient");
            } else {
                MessageDisplayer.showAcceptMessage(null, "insertedPatient");
            }

            getStage().setResult(result);
            getStage().close();
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        getStage().close();
    }

    private static final String BLANK = "";

    @FXML
    private void onAggiungi(ActionEvent event) {
        updateAll();
        numero.getItems().add(BLANK);
        numero.getSelectionModel().selectLast();
    }

    @FXML
    private void onElimina(ActionEvent event) {
        updateAll();
        int selectedItem = numero.getSelectionModel().getSelectedIndex();
        if (selectedItem > -1) {
            numero.getItems().remove(selectedItem);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numero.setCellFactory(new ListViewTextFieldFactory());
        numero.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        patologia.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addPatient"));

        idLbl.setVisible(false);

        loadPatologia();
    }

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editPatient"));

        Paziente paziente = (Paziente) parameter.getValue("paziente");

        id.setText(paziente.getId());
        nome.setText(paziente.getNome());
        cognome.setText(paziente.getCognome());
        data.setText(paziente.getData().toString(DateConverter.NORMAL_DATE_FORMAT));

        List<String> rubrica = paziente.getNumeroCellulare();
        numero.getItems().setAll(rubrica);

        for (Patologia patologiaItem : paziente.getPatologia()) {
            insertedMap.put(patologiaItem.getCodice(), patologiaItem);
        }

        loadPatologia();
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private class ListViewTextFieldFactory implements Callback<ListView<String>, ListCell<String>> {

        @Override
        public ListCell<String> call(ListView<String> stringListView) {
            return new CellTextField();
        }

        private class CellTextField extends ListCell<String> {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                int i = getIndex();
                if(!empty) {
                    final IndexedTextField numeroField = new IndexedTextField(i);
                    numeroField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                            textFieldCache.put(numeroField.i, numeroField.getText());
                        }
                    });

                    numeroField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            numero.getSelectionModel().select(numeroField.i);
                        }
                    });

                    numeroField.setText(item);

                    setGraphic(numeroField);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        }
    }

    private void updateAll() {
        Iterator<Map.Entry<Integer, String>> iterator = textFieldCache.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            numero.getItems().set(entry.getKey(), entry.getValue());
            iterator.remove();
        }
    }

    private Map<Integer, String> textFieldCache = new HashMap<>();

    private Map<String, Patologia> insertedMap = new HashMap<>();
    private ObservableList<BooleanBox<Patologia>> patologiaData = FXCollections.observableArrayList();

    private void loadPatologia() {
        List<Patologia> patologiaList = (List<Patologia>) fc.processRequest("VisualizzaTuttePatologie", null);
        for (Patologia patologiaItem : patologiaList) {
            BooleanBox<Patologia> patologiaBox = new BooleanBox<Patologia>(patologiaItem);
            boolean isContained = insertedMap.containsKey(patologiaItem.getCodice());
            patologiaBox.setChecked(isContained);

            patologiaData.add(patologiaBox);
        }

        checkedPatologia.setCellValueFactory(new PropertyValueFactory<BooleanBox<Patologia>, BooleanProperty>("checked"));
        checkedPatologia.setCellFactory(new BooleanCheckTableFactory());

        patologiaColonna.setCellValueFactory(new PropertyValueFactory<BooleanBox<Patologia>, Patologia>("element"));
        patologiaColonna.setCellFactory(new PatologiaPartialDepictionTableFactory());

        gravitaPatologia.setCellValueFactory(new PropertyValueFactory<BooleanBox<Patologia>, Patologia>("element"));
        gravitaPatologia.setCellFactory(new PatologiaGravitaDepictionTableFactory());

        patologia.setItems(patologiaData);
    }
}
