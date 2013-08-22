package business.applicationservice.exception;

import utility.MessageDisplayer;


public class InvalidInfermiereFieldException extends CommonException {
    public InvalidInfermiereFieldException(String invalidName) {
        this.invalidName = invalidName;
    }

    String invalidName;

    @Override
    public void reportException() {
        MessageDisplayer.showErrorMessage(null, invalidName);
    }
}
