package presentation.boundary.controller;

import business.entity.Infermiere;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import presentation.boundary.ReturnableStage;
import util.SimpleLabelTranslator;

import java.net.URL;
import java.util.ResourceBundle;

public class SchermataImmissioneInfermiere extends SchermataImmissione {
    private ReturnableStage stage;
    private boolean isEdit;

    @FXML private Node root;
    @FXML private Labeled titolo;

    @FXML private Labeled id;
    @FXML private TextField nome;
    @FXML private TextField cognome;

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
}
