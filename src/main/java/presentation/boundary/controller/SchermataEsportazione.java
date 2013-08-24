package presentation.boundary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.Parameter;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class SchermataEsportazione extends Schermata {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private TableView pianificazione;
    @FXML
    private TableColumn infermiere;
    @FXML
    private TableColumn numeroInterventi;


    @FXML
    private void onOk(ActionEvent event) {
        Object result = null;

        Parameter nurseParameter = new Parameter();

        //TODO
        //result = fc.processRequest("InserisciInfermiere", nurseParameter);


        if (result != null) {

            //MessageDisplayer.showAcceptMessage(null, "insertedNurse");


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

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }
}
