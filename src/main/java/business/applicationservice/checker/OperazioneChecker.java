package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidOperazioneFieldException;
import business.entity.Patologia;

import java.util.List;

class OperazioneChecker implements Checker {
    private static final int NOME = 0;
    private static final int PATOLOGIA = 1;

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;

    private List<Object> listOfValues = null;
    private boolean isValid;

    @Override
    public void check(List<Object> values) throws CommonException {
        listOfValues = values;
        checkNome();
        checkPatologia();
    }



    private void checkNome() throws InvalidOperazioneFieldException {
        String nome = (String) listOfValues.get(NOME);
        nome = nome.trim();

        int length = nome.length();

        isValid = (length >= MIN_NOME_VALUE) && (length <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidOperazioneFieldException("invalidOperationName");
        }
    }

    private void checkPatologia() throws InvalidOperazioneFieldException {
        List<Patologia> patologia = (List<Patologia>) listOfValues.get(PATOLOGIA);
        if (patologia.isEmpty()) {
            throw new InvalidOperazioneFieldException("emptyDisease");
        }
    }
}
