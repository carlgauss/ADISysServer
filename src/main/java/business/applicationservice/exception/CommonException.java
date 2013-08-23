package business.applicationservice.exception;


import utility.MessageDisplayer;

public abstract class CommonException extends Exception {
    public CommonException() {

    }

    public CommonException(String message) {
        this.message = message;
    }

    String message;

    public void reportException() {
        MessageDisplayer.showErrorMessage(null, message);
    }
}
