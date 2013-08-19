package presentation.boundary.controller;

import business.entity.Intervento;
import business.entity.Paziente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joda.time.LocalDate;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import util.InterventoColoringRowFactory;
import util.DateValueFactory;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabellaInfermiere.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabellaPaziente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabellaIntervento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //test

        List<Paziente> pazienteList = (List<Paziente>) fc.processRequest("VisualizzaTuttiPazienti", null);
        ObservableList<Paziente> pazienteData = FXCollections.observableArrayList(pazienteList);

        idPaziente.setCellValueFactory(new PropertyValueFactory<Paziente, String>("id"));
        nomePaziente.setCellValueFactory(new PropertyValueFactory<Paziente, String>("nome"));
        cognomePaziente.setCellValueFactory(new PropertyValueFactory<Paziente, String>("cognome"));
        dataPaziente.setCellValueFactory(new PropertyValueFactory<Paziente, LocalDate>("data"));
        dataPaziente.setCellFactory(new DateValueFactory<Paziente>());

        tabellaPaziente.setItems(pazienteData);

        List<Intervento> interventoList = (List<Intervento>) fc.processRequest("VisualizzaTuttiInterventi", null);
        ObservableList<Intervento> interventoData = FXCollections.observableArrayList(interventoList);

        dataIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, LocalDate>("data"));
        dataIntervento.setCellFactory(new DateValueFactory<Paziente>());
        oraIntervento.setCellValueFactory(new PropertyValueFactory<Intervento, LocalDate>("ora"));
        oraIntervento.setCellFactory(new InterventoColoringRowFactory<>());

        tabellaIntervento.setItems(interventoData);
    }

}
