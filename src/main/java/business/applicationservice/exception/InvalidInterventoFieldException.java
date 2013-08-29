package business.applicationservice.exception;


import utility.MessageDisplayer;

public class InvalidInterventoFieldException extends CommonException {
    public InvalidInterventoFieldException(String message) {
        super(message);
    }

    public InvalidInterventoFieldException(String message, String codice) {
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
            String subtitle = "errorOnToOperation";

            MessageDisplayer.showErrorMessage(null, subtitle, codice, message);
        }
    }
}
