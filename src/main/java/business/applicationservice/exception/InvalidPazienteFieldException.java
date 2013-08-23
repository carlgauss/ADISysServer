package business.applicationservice.exception;

import utility.MessageDisplayer;

public class InvalidPazienteFieldException extends CommonInvalidFieldException {
    public InvalidPazienteFieldException(String message) {
        this.message = message;
    }

    String message;

    @Override
    public void reportException() {
        MessageDisplayer.showErrorMessage(null, message);
    }
}
