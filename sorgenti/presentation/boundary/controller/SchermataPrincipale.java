package presentation.boundary.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class SchermataPrincipale implements Initializable {

    @FXML private TableColumn idInfermiere;

    @FXML private TableColumn nomeInfermiere;

    @FXML private TableColumn cognomeInfermiere;

    @FXML private TableView tabellaInfermiere;
    @FXML private TableView tabellaPaziente;
    @FXML private TableView tabellaIntervento;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabellaInfermiere.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabellaPaziente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabellaIntervento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
