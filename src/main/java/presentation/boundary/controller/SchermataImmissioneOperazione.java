package presentation.boundary.controller;

import business.entity.Operazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.MessageDisplayer;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.ResourceBundle;

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
    private void onOk(ActionEvent event) {
        Object result = null;

        Parameter operationParameter = new Parameter();

        operationParameter.setValue("nome", nome.getText());

        result = fc.processRequest("VerificaOperazione", operationParameter);

        if (result != null) {
            if (isEdit) {
                MessageDisplayer.showAcceptMessage(null, "editedOperation");
            } else {
                MessageDisplayer.showAcceptMessage(null, "insertedOperation");
                operazione = new Operazione();
            }

            operazione.setNome(nome.getText());
            operazione.setNota(nota.getText());

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

    }

    private static final String BLANK = "";

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addOperation"));
    }

    private Operazione operazione;

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editOperation"));

        Operazione operazione = (Operazione) parameter.getValue("operazione");
        this.operazione = operazione;

        nome.setText(operazione.getNome());
        nota.setText(operazione.getNota());
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }
}
