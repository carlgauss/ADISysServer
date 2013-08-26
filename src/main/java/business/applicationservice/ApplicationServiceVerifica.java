package business.applicationservice;

import business.entity.Accelerometro;
import business.entity.GPS;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.List;

public class ApplicationServiceVerifica implements ApplicationService {

    private static final int RED = -1;
    private static final int YELLOW = 0;
    private static final int GREEN = 1;

    private static final int DOMICILE = 0;
    private static final int MIN_ARRAY_SIZE = 1;

    private static final double MAX_DISTANCE = 0.03;

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

            if (result != RED) {
                result = GREEN;
            }
        }

        return result;
    }

    private static final long MAX_MINUTES = 30;
    private static final long MINUTE_IN_SECONDS = 60;
    private static final long MAX_SECONDS = MAX_MINUTES * MINUTE_IN_SECONDS;
    private static final DateTime MAX_TIME = new DateTime(MAX_SECONDS);
    private static final Duration MAX_DURATION = new Duration(new DateTime(0), MAX_TIME);

    public Integer deriveAccelerometro(Parameter parameter) {
        Integer result = YELLOW;

        List<Accelerometro> accelerometroList = (List<Accelerometro>) parameter.getValue("accelerometro");

        if (accelerometroList.size() > MIN_ARRAY_SIZE) {
            Accelerometro to = accelerometroList.get(FIRST);

            for (int i = 1; i < accelerometroList.size(); i++) {
                Accelerometro selected = accelerometroList.get(i);

                if (to.isNullSignal(selected)) {
                    Duration duration = to.getDuration(selected);

                    if (duration.isLongerThan(MAX_DURATION)) {
                        result = RED;
                        break;
                    }
                } else {
                    Duration duration = to.getDuration(selected);

                    if (duration.isLongerThan(MAX_DURATION)) {
                        result = RED;
                        break;
                    } else {
                        to = selected;
                    }
                }
            }

            result = GREEN;
        }

        return result;
    }
}
