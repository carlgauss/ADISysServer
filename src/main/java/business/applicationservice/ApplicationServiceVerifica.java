package business.applicationservice;

import business.entity.Accelerometro;
import business.entity.GPS;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.List;

public class ApplicationServiceVerifica implements ApplicationService {

    private static final int RED = -1;
    private static final int YELLOW = 0;
    private static final int GREEN = 1;

    private static final int DOMICILE = 0;
    private static final int MIN_ARRAY_SIZE = 1;

    private static final double MAX_DISTANCE = 30;

    private static final int FIRST = 0;

    public Integer deriveGPS(Parameter parameter) {
        Integer result = YELLOW;

        List<GPS> gpsList = (List<GPS>) parameter.getValue("gps");

        if (gpsList.size() > MIN_ARRAY_SIZE) {
            GPS domicile = gpsList.get(DOMICILE);

            for (int i = 1; i < gpsList.size(); i++) {
                GPS selectedGPS = gpsList.get(i);

                if (domicile.distance(selectedGPS) > MAX_DISTANCE) {
                    result = RED;
                    break;
                }
            }

            result = GREEN;
        }

        return result;
    }

    public Integer deriveAccelerometro(Parameter parameter) {
        Integer result = YELLOW;

        List<Accelerometro> accelerometroList = (List<Accelerometro>) parameter.getValue("accelerometro");

        if (accelerometroList.size() > MIN_ARRAY_SIZE) {
            Accelerometro to = accelerometroList.get(FIRST);

            //for (int i = 1;)

        }

        return result;
    }
}
