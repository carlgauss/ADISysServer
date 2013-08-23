package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidPazienteFieldException;
import org.joda.time.LocalDate;
import utility.DateConverter;

import java.util.List;

class PazienteChecker implements Checker {
    private static final int NOME = 0;
    private static final int COGNOME = 1;
    private static final int DATA = 2;

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 30;

    @Override
    public void check(List<Object> values) throws CommonException {
        String nome = (String) values.get(NOME);
        boolean isValid = (MIN_NOME_VALUE <= nome.length())
                       && (nome.length() <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidPazienteFieldException("invalidPatientName");
        }

        String cognome = (String) values.get(COGNOME);
        isValid = (MIN_COGNOME_VALUE <= cognome.length())
               && (cognome.length() <= MAX_COGNOME_VALUE);

        if (!isValid) {
            throw new InvalidPazienteFieldException("invalidPatientSurname");
        }

        String data = (String) values.get(DATA);
        try {
            LocalDate.parse(data, DateConverter.NORMAL_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidPazienteFieldException("formatDateError");
        }
    }
}
