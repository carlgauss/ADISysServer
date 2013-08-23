package presentation.boundary.controller;

import business.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
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

        Parameter patientParameter = new Parameter();

        //patientParameter.setValue("id", id.getText());
        //patientParameter.setValue("nome", nome.getText());
        //TODO
        if (isEdit) {
            //result = fc.processRequest("ModificaPaziente", patientParameter);
        } else {
            //result = fc.processRequest("InserisciPaziente", patientParameter);
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
    private void onOperazioneAdd(ActionEvent event) {

    }

    @FXML
    private void onOperazioneRemove(ActionEvent event) {
        int selectedItem = operazione.getSelectionModel().getSelectedIndex();
        if (selectedItem > -1) {
            operazione.getItems().remove(selectedItem);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operazione.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addIntervention"));

        idLbl.setVisible(false);
    }

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editIntervention"));

        Intervento intervento = (Intervento) parameter.getValue("intervento");

        id.setText(intervento.getId());
        citta.setText(intervento.getCitta());
        cap.setText(intervento.getCap());
        indirizzo.setText(intervento.getIndirizzo());
        data.setText(intervento.getData().toString(DateConverter.NORMAL_DATE_FORMAT));
        ora.setText(intervento.getOra().toString());

        List<Paziente> pazienteList = (List<Paziente>) fc.processRequest("VisualizzaTuttiPazienti", null);

        paziente.setCellFactory(new PersonPortrayalCellFactory<Paziente>());
        ObservableList<Paziente> pazienteData = FXCollections.observableArrayList(pazienteList);
        paziente.setItems(pazienteData);

        selectEntityById(intervento.getPaziente(), paziente);

        List<Infermiere> infermiereList = (List<Infermiere>) fc.processRequest("VisualizzaTuttiInfermieri", null);

        infermiere.setCellFactory(new PersonPortrayalCellFactory<Infermiere>());
        ObservableList<Infermiere> infermiereData = FXCollections.observableArrayList(infermiereList);
        infermiere.setItems(infermiereData);

        selectEntityById(intervento.getInfermiere(), infermiere);


        //List<String> rubrica = paziente.getNumeroCellulare();
        //numero.getItems().setAll(rubrica);
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private <AtomicEntity extends IndipendentEntity> void selectEntityById(AtomicEntity entity, ComboBox<AtomicEntity> entityComboBox) {
        String entityId = entity.getId();

        ObservableList<AtomicEntity> entityData = entityComboBox.getItems();

        if (!entityData.isEmpty()) {
            boolean searched = false;

            int i;
            for (i = 0; (i < entityData.size()) && !searched; i++) {
                String searchedEntityId = entityData.get(i).getId();
                searched = entityId.equals(searchedEntityId);
            }

            entityComboBox.getSelectionModel().select(i);
        }
    }

}
