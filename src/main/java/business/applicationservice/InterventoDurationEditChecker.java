package business.applicationservice;

import business.entity.Intervento;
import org.joda.time.*;


public class InterventoDurationEditChecker {

    private final static Duration MIN_EDIT_DURATION = new Duration(0, (new LocalTime(2, 0, 0).getMillisOfDay()));
    private final static DateTimeZone CURR_TIMEZONE = DateTimeZone.getDefault();
    private final static MutableDateTime MAX_DATE = new MutableDateTime(9999, 12, 31, 23, 59, 59, 0);

    public static boolean checkInterventoEditable(Intervento intervento) {
        LocalDate dataInt = intervento.getData();
        LocalTime oraInt = intervento.getOra();

        MutableDateTime dataComplInt = new MutableDateTime();

        dataComplInt.setYear(dataInt.getYear());
        dataComplInt.setDayOfYear(dataInt.getDayOfYear());
        dataComplInt.setMillisOfDay(oraInt.getMillisOfDay());

        DateTime now = DateTime.now(DateTimeZone.getDefault());
        Duration duration = new Duration(now, dataComplInt);
        return (!duration.isShorterThan(MIN_EDIT_DURATION))
                && (dataComplInt.compareTo(MAX_DATE) <= 0);
    }

    public static boolean checkDaily(Intervento intervento) {
        boolean result = false;

        LocalDate nowDate = LocalDate.now();
        LocalDate interventoDate = intervento.getData();

        result = nowDate.equals(interventoDate);

        if (result) {
            LocalTime nowTime = LocalTime.now();
            LocalTime interventoTime = intervento.getOra();

            result = (interventoTime.compareTo(nowTime) > 0);
        }

        return result;
    }
}
