package presentation.boundary.controller;

import business.entity.Infermiere;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import presentation.boundary.ReturnableStage;
import presentation.boundary.SchermataModificaInfermiere;
import util.Parameter;
import util.SimpleLabelTranslator;

import java.net.URL;
import java.util.ResourceBundle;

public class SchermataImmissioneInfermiere implements Initializable {
    private ReturnableStage stage;
    private boolean isEdit;

    @FXML private Node root;
    @FXML private Labeled titolo;

    @FXML private Labeled id;
    @FXML private Labeled nome;
    @FXML private Labeled cognome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = (ReturnableStage) root.getScene().getWindow();

        Parameter parameter = stage.getParameter();

        isEdit = stage instanceof SchermataModificaInfermiere;
        if(isEdit) {
            titolo.setText(SimpleLabelTranslator.translate("editNurse"));

            Infermiere infermiere = (Infermiere) parameter.getValue("infermiere");

            id.setText(infermiere.getId());
            nome.setText(infermiere.getNome());
            cognome.setText(infermiere.getCognome());

        } else {
            titolo.setText(SimpleLabelTranslator.translate("addNurse"));
        }
    }
}
