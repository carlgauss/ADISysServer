package business.applicationservice.checker;

import business.applicationservice.InterventoDurationEditChecker;
import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidInterventoFieldException;
import business.entity.Intervento;
import business.entity.Operazione;
import business.entity.Patologia;
import business.entity.Paziente;
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
    private static final int MAX_CAP_VALUE = 12;
    private static final int MIN_INDIRIZZO_VALUE = 3;
    private static final int MAX_INDIRIZZO_VALUE = 50;

    private static final int MIN_OPERAZIONE_SIZE = 1;

    @Override
    public void check(List<Object> values) throws CommonException {
        String citta = (String) values.get(CITTA);
        citta = citta.trim();
        boolean isValid = (MIN_CITTA_VALUE <= citta.length())
                && (citta.length() <= MAX_CITTA_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionCity");
        }

        String cap = (String) values.get(CAP);
        cap = cap.trim();
        isValid = (MIN_CAP_VALUE <= cap.length())
                && (cap.length() <= MAX_CAP_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionPostalCode");
        }

        String indirizzo = (String) values.get(INDIRIZZO);
        indirizzo = indirizzo.trim();
        isValid = (MIN_INDIRIZZO_VALUE <= indirizzo.length())
                && (indirizzo.length() <= MAX_INDIRIZZO_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionAddress");
        }

        LocalDate data = null;
        String dataString = (String) values.get(DATA);
        try {
            data = LocalDate.parse(dataString, DateConverter.NORMAL_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidInterventoFieldException("formatDateError");
        }

        LocalTime ora = null;
        String oraString = (String) values.get(ORA);
        try {
            ora = LocalTime.parse(oraString, DateConverter.EUROPEAN_TIME_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidInterventoFieldException("formatTimeError");
        }

        Paziente paziente = (Paziente) values.get(PAZIENTE);
        if (paziente == null) {
            throw new InvalidInterventoFieldException("invalidInterventionPatient");
        }

        if (values.get(INFERMIERE) == null) {
            throw new InvalidInterventoFieldException("invalidInterventionNurse");
        }

        List<Operazione> operazione = (List<Operazione>) values.get(OPERAZIONE);
        int operazioneSize = operazione.size();
        if (operazioneSize < MIN_OPERAZIONE_SIZE) {
            throw new InvalidInterventoFieldException("invalidInterventionOperation");
        }

        Intervento intervento = new Intervento();
        intervento.setData(data);
        intervento.setOra(ora);
        if (!InterventoDurationEditChecker.checkInterventoEditable(intervento)) {
            throw new InvalidInterventoFieldException("inconsistentInterventionDateTime");
        }

        List<Patologia> patologiaList = paziente.getPatologia();
        for (Operazione operazioneItem : operazione) {
            if (!patologiaList.containsAll(operazioneItem.getPatologia())) {
                throw new InvalidInterventoFieldException("inconsistentOperationDisease", operazioneItem.getNome());
            }
        }
    }
}
