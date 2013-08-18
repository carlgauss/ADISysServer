package presentation.boundary.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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

    @FXML
    Label titolo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle bundle = ResourceBundle.getBundle("presentation.boundary.markup.language.italiano");
    }
}
