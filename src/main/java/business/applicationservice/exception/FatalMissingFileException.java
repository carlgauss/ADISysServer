package business.applicationservice.exception;

import javax.swing.*;

public class FatalMissingFileException extends CommonException {
    public FatalMissingFileException(String message) {
        super(message);
    }

    private static final int ERROR = 1;

    @Override
    public void reportException() {
        JOptionPane.showMessageDialog(null, message, "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
        System.exit(ERROR);
    }
}
