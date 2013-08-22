package utility;

import business.entity.Intervento;
import org.joda.time.*;


public class InterventoDurationEditChecker {

    private final static Duration MIN_EDIT_DURATION = new Duration(0, (new LocalTime(2, 0, 0).getMillisOfDay()));
    private final static DateTimeZone CURR_TIMEZONE = DateTimeZone.getDefault();

    public static boolean checkInterventoEditable(Intervento intervento) {
        LocalDate dataInt = intervento.getData();
        LocalTime oraInt = intervento.getOra();

        MutableDateTime dataComplInt = new MutableDateTime();

        dataComplInt.setYear(dataInt.getYear());
        dataComplInt.setDayOfYear(dataInt.getDayOfYear());
        dataComplInt.setMillisOfDay(oraInt.getMillisOfDay());

        DateTime now = DateTime.now(DateTimeZone.getDefault());
        Duration duration = new Duration(now, dataComplInt);
        return duration.isShorterThan(MIN_EDIT_DURATION);
    }
}