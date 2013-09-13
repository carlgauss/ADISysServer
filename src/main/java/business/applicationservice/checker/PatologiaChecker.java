package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidPatologiaFieldException;

import java.util.List;

class PatologiaChecker implements Checker {
    private static final int CODICE = 0;
    private static final int NOME = 1;
    private static final int GRAVITA = 2;

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 20;
    private static final String GRAVITA_REGEX = "[1-5]";

    private List<Object> listOfValues = null;
    private boolean isValid;
    String codice;

    @Override
    public void check(List<Object> values) throws CommonException {
        listOfValues = values;
        codice = (String) listOfValues.get(CODICE);
        checkNome();
        checkGravita();
    }


    private void checkNome() throws InvalidPatologiaFieldException {
        String nome = (String) listOfValues.get(NOME);
        nome = nome.trim();

        int length = nome.length();

        isValid = (length >= MIN_NOME_VALUE) && (length <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidPatologiaFieldException("invalidDiseaseName", codice);
        }
    }

    private void checkGravita() throws InvalidPatologiaFieldException {
        String gravita = String.valueOf((int) listOfValues.get(GRAVITA));
        if (!gravita.matches(GRAVITA_REGEX)) {
            throw new InvalidPatologiaFieldException("invalidDiseaseGravity", codice);
        }
    }
}
