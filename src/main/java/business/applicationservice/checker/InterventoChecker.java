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

    private List<Object> listOfValues = null;
    private boolean isValid;

    @Override
    public void check(List<Object> values) throws CommonException {
        listOfValues = values;

        checkNomeCitta();
        checkCAP();
        checkIndirizzo();
        LocalDate data = checkDateAndReturnIt();
        LocalTime ora = checkLocalTimeAndReturnIt();
        checkInfermiere();
        Paziente paziente = checkPazienteAndReturnIt();
        List<Operazione> operazioni = checkOperazioneAndReturnIt();
        checkInterventoDate(data, ora);
        checkPatologie(paziente, operazioni);
    }


    private void checkNomeCitta() throws InvalidInterventoFieldException {
        String citta = (String) listOfValues.get(CITTA);
        citta = citta.trim();
        isValid = (MIN_CITTA_VALUE <= citta.length()) && (citta.length() <= MAX_CITTA_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionCity");
        }
    }


    private void checkCAP() throws InvalidInterventoFieldException {
        String cap = (String) listOfValues.get(CAP);
        cap = cap.trim();
        isValid = (MIN_CAP_VALUE <= cap.length()) && (cap.length() <= MAX_CAP_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionPostalCode");
        }

    }

    private void checkIndirizzo() throws InvalidInterventoFieldException {
        String indirizzo = (String) listOfValues.get(INDIRIZZO);
        indirizzo = indirizzo.trim();
        isValid = (MIN_INDIRIZZO_VALUE <= indirizzo.length())
                && (indirizzo.length() <= MAX_INDIRIZZO_VALUE);

        if (!isValid) {
            throw new InvalidInterventoFieldException("invalidInterventionAddress");
        }
    }

    private LocalDate checkDateAndReturnIt() throws InvalidInterventoFieldException {
        LocalDate data = null;
        String dataString = (String) listOfValues.get(DATA);
        try {
            data = LocalDate.parse(dataString, DateConverter.NORMAL_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidInterventoFieldException("formatDateError");
        }
        return data;
    }

    private LocalTime checkLocalTimeAndReturnIt() throws InvalidInterventoFieldException {
        LocalTime ora = null;
        String oraString = (String) listOfValues.get(ORA);
        try {
            ora = LocalTime.parse(oraString, DateConverter.EUROPEAN_TIME_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidInterventoFieldException("formatTimeError");
        }
        return ora;
    }


    private void checkInfermiere() throws InvalidInterventoFieldException {
        if (listOfValues.get(INFERMIERE) == null) {
            throw new InvalidInterventoFieldException("invalidInterventionNurse");
        }
    }

    private Paziente checkPazienteAndReturnIt() throws InvalidInterventoFieldException {
        Paziente paziente = (Paziente) listOfValues.get(PAZIENTE);
        if (paziente == null) {
            throw new InvalidInterventoFieldException("invalidInterventionPatient");
        }
        return paziente;
    }


    private List<Operazione> checkOperazioneAndReturnIt() throws InvalidInterventoFieldException {
        List<Operazione> operazione = (List<Operazione>) listOfValues.get(OPERAZIONE);
        int operazioneSize = operazione.size();
        if (operazioneSize < MIN_OPERAZIONE_SIZE) {
            throw new InvalidInterventoFieldException("invalidInterventionOperation");
        }
        return operazione;
    }


    private void checkInterventoDate(LocalDate data, LocalTime ora) throws InvalidInterventoFieldException {
        Intervento intervento = new Intervento();
        intervento.setData(data);
        intervento.setOra(ora);
        if (!InterventoDurationEditChecker.checkInterventoEditable(intervento)) {
            throw new InvalidInterventoFieldException("inconsistentInterventionDateTime");
        }
    }


    private void checkPatologie(Paziente paziente, List<Operazione> operazione) throws InvalidInterventoFieldException {
        List<Patologia> patologiaList = paziente.getPatologia();
        for (Operazione operazioneItem : operazione) {
            if (!patologiaList.containsAll(operazioneItem.getPatologia())) {
                throw new InvalidInterventoFieldException("inconsistentOperationDisease", operazioneItem.getNome());
            }
        }
    }
}
