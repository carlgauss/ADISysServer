package presentation.boundary.controller;

import business.entity.Infermiere;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import util.SimpleLabelTranslator;

import java.net.URL;
import java.util.ResourceBundle;

public class SchermataImmissioneInfermiere extends SchermataImmissione {
    private ReturnableStage stage;
    private boolean isEdit;

    private FrontController fc = FrontControllerFactory.buildInstance();

    @FXML private Node root;
    @FXML private Labeled titolo;

    @FXML private Labeled id;
    @FXML private TextField nome;
    @FXML private TextField cognome;

    @FXML private void onOk(ActionEvent event) {
        System.out.println("aaaaa");
    }

    @FXML private void onCancel(ActionEvent event) {
        System.out.println("bbbbb");
        getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addNurse"));
    }

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editNurse"));

        Infermiere infermiere = (Infermiere) parameter.getValue("infermiere");

        id.setText(infermiere.getId());
        nome.setText(infermiere.getNome());
        cognome.setText(infermiere.getCognome());
    }

    private Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
