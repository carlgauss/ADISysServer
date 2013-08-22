package presentation.boundary.controller;

import business.entity.Infermiere;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.MessageDisplayer;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.ResourceBundle;

public class SchermataImmissioneInfermiere extends SchermataImmissione {
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
    private void onOk(ActionEvent event) {
        Object result = null;

        Parameter nurseParameter = new Parameter();

        nurseParameter.setValue("id", id.getText());
        nurseParameter.setValue("nome", nome.getText());
        nurseParameter.setValue("cognome", cognome.getText());

        if (isEdit) {
            result = fc.processRequest("ModificaInfermiere", nurseParameter);
        } else {
            result = fc.processRequest("InserisciInfermiere", nurseParameter);
        }

        if (result != null) {
            if (isEdit) {
                MessageDisplayer.showAcceptMessage(null, "editedNurse");
            } else {
                MessageDisplayer.showAcceptMessage(null, "insertedNurse");
            }

            getStage().setResult(result);
            getStage().close();
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private static final String BLANK = "";

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addNurse"));

        idLbl.setVisible(false);
    }

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editNurse"));

        Infermiere infermiere = (Infermiere) parameter.getValue("infermiere");

        id.setText(infermiere.getId());
        nome.setText(infermiere.getNome());
        cognome.setText(infermiere.getCognome());
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }
}
