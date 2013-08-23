package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidOperazioneFieldException;

import java.util.List;

class OperazioneChecker implements Checker {
    private static final int NOME = 0;

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;

    @Override
    public void check(List<Object> values) throws CommonException {
        String nome = (String) values.get(NOME);
        nome = nome.trim();

        int length = nome.length();
        boolean isValid = (length >= MIN_NOME_VALUE)
                && (length <= MAX_NOME_VALUE);

        if (!isValid) {
            throw new InvalidOperazioneFieldException("invalidOperationName");
        }
    }
}
