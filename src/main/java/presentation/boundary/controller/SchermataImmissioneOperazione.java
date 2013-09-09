package presentation.boundary.controller;

import business.entity.Operazione;
import business.entity.Patologia;
import business.entity.Paziente;
import business.transfer.BooleanBox;
import business.transfer.OperazioneTO;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.component.TranslatedLabel;
import presentation.boundary.controller.itemfactory.BooleanCheckTableFactory;
import presentation.boundary.controller.itemfactory.PatologiaGravitaDepictionTableFactory;
import presentation.boundary.controller.itemfactory.PatologiaPartialDepictionTableFactory;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.MessageDisplayer;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.*;

public class SchermataImmissioneOperazione extends SchermataImmissione {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;
    @FXML
    private TextField nome;
    @FXML
    private TextArea nota;

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
        Object result = null;

        Parameter operationParameter = new Parameter();

        operationParameter.setValue("nome", nome.getText());

        List<Patologia> patologiaList = new ArrayList<>();

        for (BooleanBox<Patologia> patologiaBox : patologiaData) {
            if(patologiaBox.getChecked().get()) {
                patologiaList.add(patologiaBox.getElement());
            }
        }

        operationParameter.setValue("patologia", patologiaList);

        result = fc.processRequest("VerificaOperazione", operationParameter);

        if (result != null) {
            if (isEdit) {
                MessageDisplayer.showAcceptMessage(null, "editedOperation");
            } else {
                MessageDisplayer.showAcceptMessage(null, "insertedOperation");
                operazione = new OperazioneTO();
            }

            operazione.setNome(nome.getText());
            operazione.setNota(nota.getText());
            operazione.setPatologia(patologiaList);

            getStage().setResult(operazione);
            getStage().close();
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Boolean result = false;
        getStage().setResult(result);
        getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patologia.setPlaceholder(new TranslatedLabel("noDisease"));
    }

    private static final String BLANK = "";

    @Override
    public void initData(Parameter parameter) {
        this.parameter = parameter;

        if (!parameter.containsKey("operazione")) {
            initializeAdd();
            isEdit = false;
        } else {
            initializeEdit();
            isEdit = true;
        }
    }

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addOperation"));

        paziente = (Paziente) parameter.getValue("paziente");

        loadPatologia();
    }

    private Operazione operazione;
    private Paziente paziente;

    private Map<String, Patologia> insertedMap = new HashMap<>();

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editOperation"));

        Operazione operazione = (Operazione) parameter.getValue("operazione");
        Paziente paziente = (Paziente) parameter.getValue("paziente");

        this.operazione = operazione;
        this.paziente = paziente;

        nome.setText(operazione.getNome());
        nota.setText(operazione.getNota());

        for (Patologia patologiaItem : operazione.getPatologia()) {
            insertedMap.put(patologiaItem.getCodice(), patologiaItem);
        }

        loadPatologia();
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private ObservableList<BooleanBox<Patologia>> patologiaData = FXCollections.observableArrayList();

    private void loadPatologia() {
        List<Patologia> patologiaList = paziente.getPatologia();
        for (Patologia patologiaItem : patologiaList) {
            BooleanBox<Patologia> patologiaBox = new BooleanBox<Patologia>(patologiaItem);
            boolean isContained = insertedMap.containsKey(patologiaItem.getCodice());
            patologiaBox.setChecked(isContained);

            patologiaData.add(patologiaBox);

            if (isContained) {
                insertedMap.remove(patologiaItem.getCodice());
            }
        }

        if (!insertedMap.isEmpty()) {
            MessageDisplayer.showMessage(null, "missingDiseaseRiconfirm");
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
