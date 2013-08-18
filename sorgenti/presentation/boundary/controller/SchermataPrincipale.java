package presentation.boundary.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javax.swing.text.TableView;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IntegerProperty tableWidthProperty = new SimpleIntegerProperty();
        //tableWidthProperty.setValue(tabellaInfermiere.getWidth());
        //idInfermiere.prefWidthProperty().bind(tableWidthProperty.divide(4));
        //nomeInfermiere.prefWidthProperty().bind(tableWidthProperty.divide(4));
        //cognomeInfermiere.prefWidthProperty().bind(tableWidthProperty.divide(2));
    }
}
