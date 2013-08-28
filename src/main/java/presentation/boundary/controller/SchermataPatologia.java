package presentation.boundary.controller;

import business.entity.Infermiere;
import business.entity.Patologia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.MessageDisplayer;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class SchermataPatologia extends Schermata {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private TableView patologia;
    @FXML
    private TableColumn codice;
    @FXML
    private TableColumn nome;
    @FXML
    private TableColumn gravita;
    @FXML
    private TextField inputCodice;

    @FXML
    private void onAggiungi(ActionEvent event) {

    }

    @FXML
    private void onOk(ActionEvent event) {
        Object result = null;

        Parameter diseaseParameter = new Parameter();

        diseaseParameter.setValue("nome", nome.getText());

        result = fc.processRequest("ModificaInfermiere", diseaseParameter);

        if (result != null) {
            //MessageDisplayer.showAcceptMessage(null, "editedNurse");

            getStage().setResult(result);
            getStage().close();
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        getStage().close();
    }

    private Set<Patologia> editedPatologia = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    private static final String BLANK = "";


    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }
}
