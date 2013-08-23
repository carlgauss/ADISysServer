package business.applicationservice.checker;

import business.applicationservice.exception.CommonException;

import java.util.List;

class PazienteChecker implements Checker {
    private static final int NOME = 0;
    private static final int COGNOME = 1;
    private static final int DATA = 2;

    @Override
    public void check(List<Object> values) throws CommonException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
