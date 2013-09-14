package business.applicationservice.exception;

import utility.MessageDisplayer;
import utility.dialogfx.DialogFX;

import javax.swing.*;

public class FatalMissingFileException extends CommonException {
    public FatalMissingFileException(String message) {
        super(message);
    }

    private static final int ERROR = 1;

    @Override
    public void reportException() {
        DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
        dialog.setTitleText("FATAL ERROR");
        dialog.setMessage(message);
        dialog.showDialog();
        System.exit(ERROR);
    }
}
