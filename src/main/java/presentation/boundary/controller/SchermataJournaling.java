package presentation.boundary.controller;

import business.entity.Accelerometro;
import business.entity.GPS;
import business.entity.InterventoCompleto;
import business.entity.Journaling;
import business.transfer.JournalingFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.component.TreeInterventoCompletoItem;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.Parameter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SchermataJournaling extends Schermata {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private ComboBox<JournalingFile> journaling;
    @FXML
    private Button carica;
    @FXML
    private TreeView<InterventoCompleto> intervento;
    @FXML
    private TableView<GPS> gps;
    @FXML
    private TableView<Accelerometro> accelerometro;

    @FXML
    private void onExit(ActionEvent event) {
        Stage stage = getStage();
        stage.close();
    }

    @FXML
    private void onAggiorna(ActionEvent event) {
        loadJournalingList();
    }

    @FXML
    private void onCarica(ActionEvent event) {
        int pos = journaling.getSelectionModel().getSelectedIndex();
        //TODO
        String journalingFileName = journaling.getItems().get(pos).getFileName();
        Parameter parameterJournaling = new Parameter();
        parameterJournaling.setValue("journaling", journalingFileName);

        Journaling journalingEntity = (Journaling) fc.processRequest("AnalizzaJournaling", parameterJournaling);
        populateJournaling(journalingEntity);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadJournalingList();
    }

    private static final String BLANK = "";


    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private void loadJournalingList() {
        List<JournalingFile> fileList = (List<JournalingFile>) fc.processRequest("ElencaFileJournaling", null);
        ObservableList<JournalingFile> fileData = FXCollections.observableArrayList(fileList);

        if (fileData.size() > 0) {
            journaling.setDisable(false);
            carica.setDisable(false);

            journaling.setItems(fileData);
        } else {
            journaling.setDisable(true);
            carica.setDisable(true);
        }
    }

    private void populateJournaling(Journaling journalingEntity) {
        List<InterventoCompleto> list = journalingEntity.getIntervento();

        TreeItem root = new TreeItem(null);
        intervento.setRoot(root);

        for (InterventoCompleto intervento : list) {
            root.getChildren().add(new TreeInterventoCompletoItem(intervento));
        }
    }
}
