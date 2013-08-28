package business.applicationservice.exception;

import utility.MessageDisplayer;

public class InvalidPatologiaFieldException extends CommonException {
    public InvalidPatologiaFieldException(String message) {
        super(message);
    }

    public InvalidPatologiaFieldException(String message, String codice) {
        super(message);

        this.codice = codice;
    }

    private String codice;

    private static final String SEPARATOR = " ";

    @Override
    public void reportException() {
        if (codice == null) {
            super.reportException();
        } else {
            String subtitle = "errorOnToDisease";

            MessageDisplayer.showErrorMessage(null, subtitle, codice, message);
        }
    }
}
