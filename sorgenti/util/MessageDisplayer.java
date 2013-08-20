package util;

import org.thehecklers.dialogfx.DialogFX;
import org.thehecklers.dialogfx.DialogFXBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 20/08/13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class MessageDisplayer {
    public static void showMessage(String title, String text) {
        if (title == null) {
            title = SimpleFormTranslator.translate("message");
        } else {
            title = SimpleFormTranslator.translate(title);
        }

        text = SimpleFormTranslator.translate(text);

        DialogFX dialog = new DialogFX();
        dialog.setTitleText(title);
        dialog.setMessage(text);
        dialog.showDialog();
    }

    public static void showErrorMessage(String title, String text) {
        if (title == null) {
            title = SimpleFormTranslator.translate("error");
        } else {
            title = SimpleFormTranslator.translate(title);
        }

        text = SimpleFormTranslator.translate(text);

        DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
        dialog.setTitleText(title);
        dialog.setMessage(text);
        dialog.showDialog();
    }

    public static void showAcceptMessage(String title, String text) {
        if (title == null) {
            title = SimpleFormTranslator.translate("error");
        } else {
            title = SimpleFormTranslator.translate(title);
        }

        text = SimpleFormTranslator.translate(text);

        DialogFX dialog = new DialogFX(DialogFX.Type.ACCEPT);
        dialog.setTitleText(title);
        dialog.setMessage(text);
        dialog.showDialog();
    }

    private static final int ACCEPT = 0;
    private static final int CANCEL = 1;
    private static final int CONFIRM_BUTTON_NUMBER = 2;

    public static boolean showConfirmMessage(String title, String text) {
        if (title == null) {
            title = SimpleFormTranslator.translate("error");
        } else {
            title = SimpleFormTranslator.translate(title);
        }

        text = SimpleFormTranslator.translate(text);

        List<String> buttonLabels = new ArrayList<>(CONFIRM_BUTTON_NUMBER);
        buttonLabels.add(SimpleFormTranslator.translate("ok"));
        buttonLabels.add(SimpleFormTranslator.translate("cancel"));

        DialogFX dialog = new DialogFX(DialogFX.Type.QUESTION);
        dialog.setTitleText(title);
        dialog.setMessage(text);

        dialog.addButtons(buttonLabels, ACCEPT, CANCEL);

        int value = dialog.showDialog();

        return value == ACCEPT;
    }
}
