package presentation.boundary.controller;

import business.entity.Infermiere;
import business.entity.Intervento;
import business.entity.Paziente;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.joda.time.LocalDate;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import presentation.boundary.controller.itemfactory.InterventoColoringRowFactory;
import presentation.boundary.controller.itemfactory.DateDepictionFactory;
import presentation.boundary.controller.itemfactory.PersonPortrayalFactory;
import util.MessageDisplayer;
import util.SimpleFormTranslator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class SchermataPrincipale implements Initializable {
    private FrontController fc = FrontControllerFactory.buildInstance();
    @FXML private TableColumn idInfermiere;
    @FXML private TableColumn nomeInfermiere;
    @FXML private TableColumn cognomeInfermiere;

    @FXML private TableColumn idPaziente;
    @FXML private TableColumn nomePaziente;
    @FXML private TableColumn cognomePaziente;
    @FXML private TableColumn dataPaziente;

    @FXML private TableColumn idIntervento;
    @FXML private TableColumn cittaIntervento;
    @FXML private TableColumn capIntervento;
    @FXML private TableColumn indirizzoIntervento;
    @FXML private TableColumn dataIntervento;
    @FXML private TableColumn oraIntervento;
    @FXML private TableColumn pazienteIntervento;
    @FXML private TableColumn infermiereIntervento;

    @FXML private TableView tabellaInfermiere;
    @FXML private TableView tabellaPaziente;
    @FXML private TableView tabellaIntervento;

    @FXML private Button esci;

    @FXML private void onInserisciInfermiere(ActionEvent event) {
        System.out.println(selectedInfermiere.getId());
    }

    @FXML private void onModificaInfermiere(ActionEvent event) {
        System.out.println(selectedInfermiere.getId());
    }

    @FXML private void onInserisciPaziente(ActionEvent event) {
        System.out.println(selectedPaziente.getId());
    }

    @FXML private void onModificaPaziente(ActionEvent event) {
        System.out.println(selectedPaziente.getId());
    }

    @FXML private void onInserisciIntervento(ActionEvent event) {
        System.out.println(selectedIntervento.getId());
    }

    @FXML private void onModificaIntervento(ActionEvent event) {
        System.out.println(selectedIntervento.getId());
    }

    @FXML private void onSetItaliano(ActionEvent event) {
        SimpleFormTranslator.setLanguage("italiano");
        MessageDisplayer.showMessage(null, "translateNextRestart");
    }

    @FXML private void onSetEnglish(ActionEvent event) {
        SimpleFormTranslator.setLanguage("english");
        MessageDisplayer.showMessage(null, "translateNextRestart");
    }

    @FXML private void onAggiorna(ActionEvent event) {
        loadAllTables();
    }

    @FXML private void onExit(ActionEvent event) {
        Stage stage = getStage();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabellaInfermiere.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabellaPaziente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabellaIntervento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabellaInfermiere.getSelectionModel().selectedItemProperty().addListener(new onSelectedInfermiereListener());
        tabellaPaziente.getSelectionModel().selectedItemProperty().addListener(new onSelectedPazienteListener());
        tabellaIntervento.getSelectionModel().selectedItemProperty().addListener(new onSelectedInterventoListener());

        loadAllTables();
    }

    private synchronized void loadAllTables() {
        //Paziente
        List<Paziente> pazienteList = (List<Paziente>) fc.processRequest("VisualizzaTuttiPazienti", null);
        ObservableList<Paziente> pazienteData = FXCollections.observableArrayList(pazienteList);

        idPaziente.setCellValueFactory(new PropertyValueFactory<Paziente, String>("id"));
        nomePaziente.setCellValueFactory(new PropertyValueFactory<Paziente, String>("nome"));
        cognomePaziente.setCellValueFactory(new PropertyValueFactory<Paziente, String>("cognome"));

        dataPaziente.setCellValueFactory(new PropertyValueFactory<Paziente, LocalDate>("data"));
        dataPaziente.setCellFactory(new DateDepictionFactory<Paziente>());

        tabellaPaziente.setItems(pazienteData);

        //Infermiere
        List<Infermiere> infermiereList = (List<Infermiere>) fc.processRequest("VisualizzaTuttiInfermieri", null);
        ObservableList<Infermiere> infermiereData = FXCollections.observableArrayList(infermiereList);

        idInfermiere.setCellValueFactory(new PropertyValueFactory<Infermiere, String>("id"));
        nomeInfermiere.setCellValueFactory(new PropertyValueFactory<Infermiere, String>("nome"));
        cognomeInfermiere.setCellValueFactory(new PropertyValueFactory<Infermiere, String>("cognome"));

        tabellaInfermiere.setItems(infermiereData);

        //Intervento
        List<Intervento> interventoList = (List<Intervento>) fc.processRequest("VisualizzaTuttiInterventi", null);
        ObservableList<Intervento> interventoData = FXCollections.observableArrayList(interventoList);

        idIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("id"));
        cittaIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("citta"));
        capIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("cap"));
        indirizzoIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("indirizzo"));

        dataIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, LocalDate>("data"));
        dataIntervento.setCellFactory(new DateDepictionFactory<Paziente>());

        oraIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, LocalDate>("ora"));
        oraIntervento.setCellFactory(new InterventoColoringRowFactory<>());

        pazienteIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, Paziente>("paziente"));
        pazienteIntervento.setCellFactory(new PersonPortrayalFactory<>());
        infermiereIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, Infermiere>("infermiere"));
        infermiereIntervento.setCellFactory(new PersonPortrayalFactory<>());

        tabellaIntervento.setItems(interventoData);
    }

    private Infermiere selectedInfermiere = null;
    private Paziente selectedPaziente = null;
    private Intervento selectedIntervento = null;

    private class onSelectedInfermiereListener implements ChangeListener<Infermiere> {

        @Override
        public void changed(ObservableValue<? extends Infermiere> observableValue, Infermiere oldInfermiere, Infermiere newInfermiere) {
            selectedInfermiere = newInfermiere;
        }
    }

    private class onSelectedPazienteListener implements ChangeListener<Paziente> {

        private ObservableValue<? extends Paziente> observableValue;
        private Paziente oldPaziente;

        @Override
        public void changed(ObservableValue<? extends Paziente> observableValue, Paziente oldPaziente, Paziente newPaziente) {
            selectedPaziente = newPaziente;
        }
    }

    private class onSelectedInterventoListener implements ChangeListener<Intervento> {

        @Override
        public void changed(ObservableValue<? extends Intervento> observableValue, Intervento oldIntervento, Intervento newIntervento) {
            selectedIntervento = newIntervento;
        }
    }

    private Stage getStage() {
        return (Stage) esci.getScene().getWindow();
    }
}
