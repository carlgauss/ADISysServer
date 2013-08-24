package presentation.boundary.controller;

import business.entity.Infermiere;
import business.entity.Intervento;
import business.transfer.PianificazioneElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.itemfactory.ListTableCountFactory;
import presentation.boundary.controller.itemfactory.PersonPortrayalTableFactory;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.List;
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
        titolo.setText(SimpleLabelTranslator.translate("exportPlanification"));

        List<PianificazioneElement> pianificazioneList = (List<PianificazioneElement>) fc.processRequest("showPianificazione", null);
        ObservableList<PianificazioneElement> pianificazioneData = FXCollections.observableArrayList(pianificazioneList);

        infermiere.setCellValueFactory(new PropertyValueFactory<PianificazioneElement, Infermiere>("infermiere"));
        infermiere.setCellFactory(new PersonPortrayalTableFactory<>());

        numeroInterventi.setCellValueFactory(new PropertyValueFactory<PianificazioneElement, List<Intervento>>("pianificazione"));
        numeroInterventi.setCellFactory(new ListTableCountFactory());

        pianificazione.setItems(pianificazioneData);
        String noValidNurse = SimpleLabelTranslator.translate("noValidNurse");
        pianificazione.setPlaceholder(new Label(noValidNurse));
    }

    private static final String BLANK = "";

    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }
}
