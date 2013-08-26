package presentation.boundary.controller.component;

import business.entity.GPS;
import javafx.scene.control.Label;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.Parameter;
import utility.SimpleLabelTranslator;

import java.util.List;

public class GPSLabel extends Label {

    private static final int RED = -1;
    private static final int YELLOW = 0;
    private static final int GREEN = 1;

    private FrontController fc = FrontControllerFactory.getFrontController();

    GPSLabel(List<GPS> gps) {
        super(SimpleLabelTranslator.translate("gps"));

        Parameter parameter = new Parameter();
        parameter.setValue("gps", gps);

        Integer signal = (Integer) fc.processRequest("AnalizzaGPS", parameter);

        if (signal.equals(GREEN)) {
            getStyleClass().add("green");
        } else if (signal.equals(YELLOW)) {
            getStyleClass().add("yellow");
        } else if (signal.equals(RED)) {
            getStyleClass().add("red");
        }
    }

}
