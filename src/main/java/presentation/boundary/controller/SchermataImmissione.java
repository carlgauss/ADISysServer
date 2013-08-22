package presentation.boundary.controller;

import javafx.fxml.Initializable;
import util.Parameter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 22/08/13
 * Time: 01:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class SchermataImmissione implements Initializable {
    protected Parameter parameter;

    protected boolean isEdit;

    @Override
    public abstract void initialize(URL url, ResourceBundle resourceBundle);

    public void initData(Parameter parameter) {
        this.parameter = parameter;

        if (parameter == null) {
            initializeAdd();
            isEdit = false;
        } else {
            initializeEdit();
            isEdit = true;
        }
    }

    protected abstract void initializeAdd();

    protected abstract void initializeEdit();
}