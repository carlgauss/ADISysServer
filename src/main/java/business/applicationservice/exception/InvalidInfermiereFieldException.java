package business.applicationservice.exception;

import utility.MessageDisplayer;


public class InvalidInfermiereFieldException extends CommonInvalidFieldException {
    public InvalidInfermiereFieldException(String message) {
        this.message = message;
    }

    String message;

    @Override
    public void reportException() {
        MessageDisplayer.showErrorMessage(null, message);
    }
}
