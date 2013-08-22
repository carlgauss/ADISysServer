package business.applicationservice.exception;

import utility.MessageDisplayer;

public class InvalidPazienteFieldException extends CommonException {
    public InvalidPazienteFieldException(String message) {
        this.message = message;
    }

    String message;

    @Override
    public void reportException() {
        MessageDisplayer.showErrorMessage(null, message);
    }
}
