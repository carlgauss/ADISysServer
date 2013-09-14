package business.applicationservice.exception;

import utility.MessageDisplayer;

import javax.swing.*;

public class FatalMissingFileException extends CommonException {
    public FatalMissingFileException(String message) {
        super(message);
    }

    private static final int ERROR = 1;

    @Override
    public void reportException() {
        MessageDisplayer.showErrorMessage("FATAL ERROR", message);
        System.exit(ERROR);
    }
}
