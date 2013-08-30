package presentation.boundary.controller.component;

import business.entity.Accelerometro;
import javafx.scene.control.Label;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

import java.util.List;

public class AccelerometroLabel extends Label {

    private static final int RED = -1;
    private static final int YELLOW = 0;
    private static final int GREEN = 1;

    private FrontController fc = FrontControllerFactory.getFrontController();

    AccelerometroLabel(List<Accelerometro> accelerometro) {
        super(SimpleLabelTranslator.translate("accelerometer"));

        Parameter parameter = new Parameter();
        parameter.setValue("accelerometro", accelerometro);

        Integer signal = (Integer) fc.processRequest("AnalizzaAccelerometro", parameter);

        if (signal.equals(GREEN)) {
            getStyleClass().add("green");
        } else if (signal.equals(YELLOW)) {
            getStyleClass().add("yellow");
        } else if (signal.equals(RED)) {
            getStyleClass().add("red");
        }
    }

}
