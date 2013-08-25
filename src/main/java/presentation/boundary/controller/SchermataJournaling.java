package presentation.boundary.controller;

import business.entity.Accelerometro;
import business.entity.GPS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SchermataJournaling extends Schermata {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private ComboBox journaling;
    @FXML
    private TreeView intervento;
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
        //TODO
    }

    @FXML
    private void onCarica(ActionEvent event) {
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem item = new TreeItem(new Label("aaaa"));
        intervento.setRoot(item);

        TreeItem item2 = new TreeItem(new Label("bbbb"));
        item.getChildren().add(item2);
    }

    private static final String BLANK = "";


    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }
}
