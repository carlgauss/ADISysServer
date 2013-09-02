package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidPazienteFieldException;
import business.entity.Patologia;
import org.joda.time.LocalDate;
import utility.DateConverter;

import java.util.List;

class PazienteChecker implements Checker {
    private static final int NOME = 0;
    private static final int COGNOME = 1;
    private static final int DATA = 2;
    private static final int NUMERO = 3;
    private static final int PATOLOGIA = 4;

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 30;

    private List<Object> listOfValues = null;
    private boolean isValid;

    @Override
    public void check(List<Object> values) throws CommonException {
        listOfValues = values;
        checkNome();
        checkCognome();
        checkData();
        checkPatologia();
    }


    private void checkNome() throws InvalidPazienteFieldException {
        String nome = (String) listOfValues.get(NOME);
        nome = nome.trim();
        isValid = (MIN_NOME_VALUE <= nome.length())
                && (nome.length() <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidPazienteFieldException("invalidPatientName");
        }
    }


    private void checkCognome() throws InvalidPazienteFieldException {
        String cognome = (String) listOfValues.get(COGNOME);
        cognome = cognome.trim();
        isValid = (MIN_COGNOME_VALUE <= cognome.length())
                && (cognome.length() <= MAX_COGNOME_VALUE);

        if (!isValid) {
            throw new InvalidPazienteFieldException("invalidPatientSurname");
        }
    }

    private void checkData() throws InvalidPazienteFieldException {
        String data = (String) listOfValues.get(DATA);
        try {
            LocalDate.parse(data, DateConverter.NORMAL_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidPazienteFieldException("formatDateError");
        }
    }

    private void checkPatologia() throws InvalidPazienteFieldException {
        List<Patologia> patologia = (List<Patologia>) listOfValues.get(PATOLOGIA);
        if (patologia.isEmpty()) {
            throw new InvalidPazienteFieldException("emptyDisease");
        }
    }
}
