package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidInfermiereFieldException;

import java.util.List;

class InfermiereChecker implements Checker {
    private static final int NOME = 0;
    private static final int COGNOME = 1;

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
    }

    private void checkCognome() throws InvalidInfermiereFieldException {
        int length;

        String cognome = (String) listOfValues.get(COGNOME);
        cognome = cognome.trim();

        length = cognome.length();

        isValid = (length >= MIN_COGNOME_VALUE)
                && (length <= MAX_COGNOME_VALUE);

        if (!isValid) {
            throw new InvalidInfermiereFieldException("invalidNurseSurname");
        }
    }

    private void checkNome() throws InvalidInfermiereFieldException {
        String nome = (String) listOfValues.get(NOME);
        nome = nome.trim();

        int length = nome.length();
        isValid = (length >= MIN_NOME_VALUE)
                && (length <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidInfermiereFieldException("invalidNurseName");
        }
    }
}
