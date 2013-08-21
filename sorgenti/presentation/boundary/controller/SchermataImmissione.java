package presentation.boundary.controller;

import business.entity.Infermiere;
import javafx.fxml.Initializable;
import presentation.boundary.ReturnableStage;
import presentation.boundary.SchermataModificaInfermiere;
import util.Parameter;
import util.SimpleLabelTranslator;

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

    @Override
    public abstract void initialize(URL url, ResourceBundle resourceBundle);

    public void initData(Parameter parameter) {
        this.parameter = parameter;

        if (parameter == null) {
            initializeAdd();
        } else {
            initializeEdit();
        }
    }

    protected abstract void initializeAdd();

    protected abstract void initializeEdit();
}
