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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.joda.time.LocalDate;
import presentation.boundary.controller.itemfactory.DateDepictionFactory;
import presentation.boundary.controller.itemfactory.InterventoColoringRowFactory;
import presentation.boundary.controller.itemfactory.PersonPortrayalTableFactory;
import presentation.boundary.controller.itemfactory.TimeDepictionFactory;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.MessageDisplayer;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SchermataPrincipale implements Initializable {
    private FrontController fc = FrontControllerFactory.getFrontController();
    @FXML
    private Node root;

    @FXML
    private TableColumn idInfermiere;
    @FXML
    private TableColumn nomeInfermiere;
    @FXML
    private TableColumn cognomeInfermiere;

    @FXML
    private TableColumn idPaziente;
    @FXML
    private TableColumn nomePaziente;
    @FXML
    private TableColumn cognomePaziente;
    @FXML
    private TableColumn dataPaziente;

    @FXML
    private TableColumn idIntervento;
    @FXML
    private TableColumn cittaIntervento;
    @FXML
    private TableColumn capIntervento;
    @FXML
    private TableColumn indirizzoIntervento;
    @FXML
    private TableColumn dataIntervento;
    @FXML
    private TableColumn oraIntervento;
    @FXML
    private TableColumn pazienteIntervento;
    @FXML
    private TableColumn infermiereIntervento;

    @FXML
    private TableView tabellaInfermiere;
    @FXML
    private TableView tabellaPaziente;
    @FXML
    private TableView tabellaIntervento;

    @FXML
    private TextField cercaInfermiere;
    @FXML
    private TextField cercaData;

    @FXML
    private Button esci;

    @FXML
    private void onInserisciInfermiere(ActionEvent event) {
        Object bool = fc.processRequest("MostraSchermataInserimentoInfermiere", null);

        if (bool != null) {
            loadAllTables();
        }
    }

    @FXML
    private void onModificaInfermiere(ActionEvent event) {
        Parameter parameter = new Parameter();
        if (selectedInfermiere != null) {
            parameter.setValue("infermiere", selectedInfermiere);
            Object bool = fc.processRequest("MostraSchermataModificaInfermiere", parameter);

            if (bool != null) {
                loadAllTables();
            }
        } else {
            MessageDisplayer.showErrorMessage(null, "selectNurse");
        }
    }

    @FXML
    private void onInserisciPaziente(ActionEvent event) {
        Object bool = fc.processRequest("MostraSchermataInserimentoPaziente", null);

        if (bool != null) {
            loadAllTables();
        }
    }

    @FXML
    private void onModificaPaziente(ActionEvent event) {
        Parameter parameter = new Parameter();
        if (selectedPaziente != null) {
            parameter.setValue("paziente", selectedPaziente);
            Object bool = fc.processRequest("MostraSchermataModificaPaziente", parameter);

            if (bool != null) {
                loadAllTables();
            }
        } else {
            MessageDisplayer.showErrorMessage(null, "selectPatient");
        }
    }

    @FXML
    private void onInserisciIntervento(ActionEvent event) {
        Object bool = fc.processRequest("MostraSchermataInserimentoIntervento", null);

        if (bool != null) {
            loadAllTables();
        }
    }

    @FXML
    private void onModificaIntervento(ActionEvent event) {
        Parameter parameter = new Parameter();
        if (selectedIntervento != null) {
            parameter.setValue("intervento", selectedIntervento);
            Object bool = fc.processRequest("MostraSchermataModificaIntervento", parameter);

            if (bool != null) {
                loadAllTables();
            }
        } else {
            MessageDisplayer.showErrorMessage(null, "selectIntervention");
        }
    }

    @FXML
    private void onCancellaIntervento(ActionEvent event) {
        Parameter parameter = new Parameter();
        if (selectedIntervento != null) {
            parameter.setValue("id", selectedIntervento.getId());

            boolean confirm = MessageDisplayer.showConfirmMessage(null, "reallySureToDeleteIntervention");

            Object bool = null;

            if (confirm) {
                bool = fc.processRequest("CancellaIntervento", parameter);
            }

            if (bool != null) {
                MessageDisplayer.showAcceptMessage(null, "deletedIntervention");
                loadAllTables();
            }
        } else {
            MessageDisplayer.showErrorMessage(null, "selectIntervention");
        }
    }

    @FXML
    private void onSetItaliano(ActionEvent event) {
        SimpleLabelTranslator.setLanguage("italiano");
        MessageDisplayer.showMessage(null, "translateNextRestart");
    }

    @FXML
    private void onSetEnglish(ActionEvent event) {
        SimpleLabelTranslator.setLanguage("english");
        MessageDisplayer.showMessage(null, "translateNextRestart");
    }

    @FXML
    private void onAggiorna(ActionEvent event) {
        loadAllTables();
    }

    @FXML
    private void onEsporta(ActionEvent event) {
        fc.processRequest("MostraSchermataEsportazione", null);
    }

    @FXML
    private void onAnalizza(ActionEvent event) {
        fc.processRequest("MostraSchermataJournaling", null);
    }

    @FXML
    private void onExit(ActionEvent event) {
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

        String noPatient = SimpleLabelTranslator.translate("noPatient");
        tabellaPaziente.setPlaceholder(new Label(noPatient));

        String noNurse = SimpleLabelTranslator.translate("noNurse");
        tabellaInfermiere.setPlaceholder(new Label(noNurse));

        String noIntervention = SimpleLabelTranslator.translate("noIntervention");
        tabellaIntervento.setPlaceholder(new Label(noIntervention));

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
        idIntervento.setCellFactory(new InterventoColoringRowFactory<>());

        cittaIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("citta"));
        capIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("cap"));
        indirizzoIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, String>("indirizzo"));

        dataIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, LocalDate>("data"));
        dataIntervento.setCellFactory(new DateDepictionFactory<>());

        oraIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, LocalDate>("ora"));
        oraIntervento.setCellFactory(new TimeDepictionFactory<>());

        pazienteIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, Paziente>("paziente"));
        pazienteIntervento.setCellFactory(new PersonPortrayalTableFactory<>());
        infermiereIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, Infermiere>("infermiere"));
        infermiereIntervento.setCellFactory(new PersonPortrayalTableFactory<>());

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
