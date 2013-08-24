package presentation.boundary.controller;

import business.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.itemfactory.PersonPortrayalCellFactory;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.DateConverter;
import utility.MessageDisplayer;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SchermataImmissioneIntervento extends SchermataImmissione {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;
    @FXML
    private AnchorPane nonModificabile;
    @FXML
    private GridPane generale;
    @FXML
    private Button ok;

    @FXML
    private Labeled idLbl;
    @FXML
    private Labeled id;
    @FXML
    private TextField citta;
    @FXML
    private TextField cap;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField data;
    @FXML
    private TextField ora;
    @FXML
    private ComboBox<Infermiere> infermiere;
    @FXML
    private ComboBox<Paziente> paziente;
    @FXML
    private ListView<Operazione> operazione;

    @FXML
    private void onOk(ActionEvent event) {
        Object result = null;

        Parameter interventionParameter = new Parameter();

        interventionParameter.setValue("id", id.getText());
        interventionParameter.setValue("citta", citta.getText());
        interventionParameter.setValue("cap", cap.getText());
        interventionParameter.setValue("indirizzo", indirizzo.getText());
        interventionParameter.setValue("data", data.getText());
        interventionParameter.setValue("ora", ora.getText());
        interventionParameter.setValue("infermiere", infermiere.getSelectionModel().getSelectedItem());
        interventionParameter.setValue("paziente", paziente.getSelectionModel().getSelectedItem());
        interventionParameter.setValue("operazione", operazione.getItems());

        if (isEdit) {
            result = fc.processRequest("ModificaIntervento", interventionParameter);
        } else {
            result = fc.processRequest("InserisciIntervento", interventionParameter);
        }

        if (result != null) {
            if (isEdit) {
                MessageDisplayer.showAcceptMessage(null, "editedIntervention");
            } else {
                MessageDisplayer.showAcceptMessage(null, "insertedIntervention");
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
    private void onOperazioneAdd(ActionEvent event) {
        Object result = fc.processRequest("MostraSchermataInserimentoOperazione", null);
        if ((result != null) && (result instanceof Operazione)) {
            Operazione resultOperazione = (Operazione) result;
            operazione.getItems().add(resultOperazione);
        }
    }

    @FXML
    private void onOperazioneEdit(ActionEvent event) {
        int selectedItem = operazione.getSelectionModel().getSelectedIndex();
        if (selectedItem > -1) {
            Operazione selectedOperazione = operazione.getItems().get(selectedItem);
            Parameter operationParameter = new Parameter();
            operationParameter.setValue("operazione", selectedOperazione);
            fc.processRequest("MostraSchermataModificaOperazione", operationParameter);
            forceOperazioneRefresh();
        } else {
            MessageDisplayer.showErrorMessage(null, "selectOperation");
        }
    }

    @FXML
    private void onOperazioneRemove(ActionEvent event) {
        int selectedItem = operazione.getSelectionModel().getSelectedIndex();
        if (selectedItem > -1) {
            boolean confirm = MessageDisplayer.showConfirmMessage(null, "sureOperationDelete");
            if (confirm) {
                operazione.getItems().remove(selectedItem);
            }
        } else {
            MessageDisplayer.showErrorMessage(null, "selectOperation");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operazione.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<Paziente> pazienteList = (List<Paziente>) fc.processRequest("VisualizzaTuttiPazienti", null);

        paziente.setCellFactory(new PersonPortrayalCellFactory<Paziente>());
        ObservableList<Paziente> pazienteData = FXCollections.observableArrayList(pazienteList);
        paziente.setItems(pazienteData);


        List<Infermiere> infermiereList = (List<Infermiere>) fc.processRequest("VisualizzaTuttiInfermieri", null);

        infermiere.setCellFactory(new PersonPortrayalCellFactory<Infermiere>());
        ObservableList<Infermiere> infermiereData = FXCollections.observableArrayList(infermiereList);
        infermiere.setItems(infermiereData);
    }

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addIntervention"));

        idLbl.setVisible(false);
    }

    private Intervento intervento;

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editIntervention"));

        intervento = (Intervento) parameter.getValue("intervento");

        id.setText(intervento.getId());
        citta.setText(intervento.getCitta());
        cap.setText(intervento.getCap());
        indirizzo.setText(intervento.getIndirizzo());
        data.setText(intervento.getData().toString(DateConverter.NORMAL_DATE_FORMAT));
        ora.setText(intervento.getOra().toString(DateConverter.EUROPEAN_TIME_FORMAT));

        selectEntityById(intervento.getPaziente(), paziente);

        selectEntityById(intervento.getInfermiere(), infermiere);

        List<Operazione> operazioneList = intervento.getOperazione();
        operazione.getItems().setAll(operazioneList);

        if (!intervento.isEditable()) {
            generale.setDisable(true);
            ok.setDisable(true);
            nonModificabile.setVisible(true);
        }
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private static final int FIRST = 0;
    private static final int NULL = -1;

    private <AtomicEntity extends IndipendentEntity> void selectEntityById(AtomicEntity entity, ComboBox<AtomicEntity> entityComboBox) {
        String entityId = entity.getId();


        ObservableList<AtomicEntity> entityData = entityComboBox.getItems();

        if (!entityData.isEmpty()) {
            boolean searched = false;

            int selectedPosition = NULL;
            for (int i = FIRST; (i < entityData.size()) && !searched; i++) {
                String searchedEntityId = entityData.get(i).getId();
                searched = entityId.equals(searchedEntityId);
                selectedPosition = i;
            }

            entityComboBox.getSelectionModel().select(selectedPosition);
        }
    }

    private void forceOperazioneRefresh() {
        ObservableList<Operazione> operazioneData = operazione.getItems();
        operazione.setItems(null);
        operazione.setItems(operazioneData);
    }
}
