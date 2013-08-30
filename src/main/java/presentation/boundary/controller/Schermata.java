package presentation.boundary.controller;

import javafx.fxml.Initializable;
import business.transfer.Parameter;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Schermata implements Initializable {
    @Override
    public abstract void initialize(URL url, ResourceBundle resourceBundle);

    protected Parameter parameter;

    public void initData(Parameter parameter) {
        this.parameter = parameter;
    }
}
