package presentation.boundary.controller;

import business.entity.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import presentation.boundary.ReturnableStage;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.DateConverter;
import utility.MessageDisplayer;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SchermataImmissionePaziente extends SchermataImmissione {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    //private Timeline animation;

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private Labeled id;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField data;
    @FXML
    private ListView numero;

    @FXML
    private void onOk(ActionEvent event) {
        Object result = null;

        Parameter nurseParameter = new Parameter();

        //nurseParameter.setValue("id", id.getText());
        //nurseParameter.setValue("nome", nome.getText());
        //nurseParameter.setValue("cognome", cognome.getText());

        if (isEdit) {
            //result = fc.processRequest("ModificaInfermiere", nurseParameter);
        } else {
            //result = fc.processRequest("InserisciInfermiere", nurseParameter);
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

    private static final String BLANK = "";

    @FXML
    private void onAggiungi(ActionEvent event) {
        numero.getItems().add(BLANK);
        numero.getSelectionModel().selectLast();
    }

    @FXML
    private void onElimina(ActionEvent event) {
        int selectedItem = numero.getSelectionModel().getSelectedIndex();
        if (selectedItem > -1) {
            numero.getItems().remove(selectedItem);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numero.setCellFactory(TextFieldListCell.forListView());
        numero.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    protected void initializeAdd() {
        titolo.setText(SimpleLabelTranslator.translate("addPatient"));

        id.setVisible(false);
    }

    @Override
    protected void initializeEdit() {
        titolo.setText(SimpleLabelTranslator.translate("editPatient"));

        Paziente paziente = (Paziente) parameter.getValue("paziente");

        id.setText(paziente.getId());
        nome.setText(paziente.getNome());
        cognome.setText(paziente.getCognome());
        data.setText(paziente.getData().toString(DateConverter.NORMAL_DATE_FORMAT));

        List<String> rubrica = paziente.getNumeroCellulare();
        numero.getItems().setAll(rubrica);
    }

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

}
