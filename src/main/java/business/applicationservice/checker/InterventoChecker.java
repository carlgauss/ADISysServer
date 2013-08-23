package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidInterventoFieldException;
import business.entity.Operazione;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import utility.DateConverter;

import java.util.List;

class InterventoChecker implements Checker {
    private static final int CITTA = 0;
    private static final int CAP = 1;
    private static final int INDIRIZZO = 2;
    private static final int DATA = 3;
    private static final int ORA = 4;
    private static final int INFERMIERE = 5;
    private static final int PAZIENTE = 6;
    private static final int OPERAZIONE = 7;

    private static final int MIN_CITTA_VALUE = 2;
    private static final int MAX_CITTA_VALUE = 30;
    private static final int MIN_CAP_VALUE = 3;
    private static final int MAX_CAP_VALUE = 30;
    private static final int MIN_INDIRIZZO_VALUE = 3;
    private static final int MAX_INDIRIZZO_VALUE = 50;

    private static final int MIN_OPERAZIONE_SIZE = 1;

    @Override
    public void check(List<Object> values) throws CommonException {
        String citta = (String) values.get(CITTA);
        boolean isValid = (MIN_CITTA_VALUE <= citta.length())
                && (citta.length() <= MAX_CITTA_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionCity");
        }

        String cap = (String) values.get(CAP);
        isValid = (MIN_CAP_VALUE <= cap.length())
                && (cap.length() <= MAX_CAP_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionPostalCode");
        }

        String indirizzo = (String) values.get(INDIRIZZO);
        isValid = (MIN_INDIRIZZO_VALUE <= indirizzo.length())
                && (indirizzo.length() <= MAX_INDIRIZZO_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionAddress");
        }

        String data = (String) values.get(DATA);
        try {
            LocalDate.parse(data, DateConverter.NORMAL_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidInterventoFieldException("formatDateError");
        }

        String ora = (String) values.get(ORA);
        try {
            LocalTime.parse(ora, DateConverter.EUROPEAN_TIME_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidInterventoFieldException("formatTimeError");
        }

        if (values.get(INFERMIERE) == null) {
            throw new InvalidInterventoFieldException("invalidInterventionNurse");
        }

        if (values.get(PAZIENTE) == null) {
            throw new InvalidInterventoFieldException("invalidInterventionPatient");
        }

        List<Operazione> operazione = (List<Operazione>) values.get(OPERAZIONE);
        int operazioneSize = operazione.size();
        if (operazioneSize < MIN_OPERAZIONE_SIZE) {
            throw new InvalidInterventoFieldException("invalidInterventionOperation");
        }
    }
}
