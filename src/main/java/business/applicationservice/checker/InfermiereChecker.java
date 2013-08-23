package business.applicationservice.checker;

import business.applicationservice.exception.CommonInvalidFieldException;
import business.applicationservice.exception.InvalidInfermiereFieldException;

import java.util.List;

class InfermiereChecker implements Checker {
    private static final int NOME = 0;
    private static final int COGNOME = 1;

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 30;

    @Override
    public void check(List<Object> values) throws CommonInvalidFieldException {
        String nome = (String) values.get(NOME);
        nome = nome.trim();

        int length = nome.length();
        boolean isValid = (length >= MIN_NOME_VALUE)
                       && (length <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidInfermiereFieldException("invalidNurseName");
        }

        String cognome = (String) values.get(COGNOME);
        cognome = cognome.trim();

        length = cognome.length();

        isValid = (length >= MIN_COGNOME_VALUE)
               && (length <= MAX_COGNOME_VALUE);

        if (!isValid) {
            throw new InvalidInfermiereFieldException("invalidNurseSurname");
        }
    }
}
