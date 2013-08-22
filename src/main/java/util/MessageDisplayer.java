package util;

import util.dialogfx.DialogFX;

import java.util.ArrayList;
import java.util.List;


public class MessageDisplayer {
    public static void showMessage(String title, String text) {
        if (title == null) {
            title = SimpleLabelTranslator.translate("message");
        } else {
            title = SimpleLabelTranslator.translate(title);
        }

        text = SimpleLabelTranslator.translate(text);

        DialogFX dialog = new DialogFX();
        dialog.setTitleText(title);
        dialog.setMessage(text);
        dialog.showDialog();
    }

    public static void showErrorMessage(String title, String text) {
        if (title == null) {
            title = SimpleLabelTranslator.translate("error");
        } else {
            title = SimpleLabelTranslator.translate(title);
        }

        text = SimpleLabelTranslator.translate(text);

        DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
        dialog.setTitleText(title);
        dialog.setMessage(text);
        dialog.showDialog();
    }

    public static void showAcceptMessage(String title, String text) {
        if (title == null) {
            title = SimpleLabelTranslator.translate("success");
        } else {
            title = SimpleLabelTranslator.translate(title);
        }

        text = SimpleLabelTranslator.translate(text);

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
            title = SimpleLabelTranslator.translate("confirm");
        } else {
            title = SimpleLabelTranslator.translate(title);
        }

        text = SimpleLabelTranslator.translate(text);

        List<String> buttonLabels = new ArrayList<>(CONFIRM_BUTTON_NUMBER);
        buttonLabels.add(SimpleLabelTranslator.translate("ok"));
        buttonLabels.add(SimpleLabelTranslator.translate("cancel"));

        DialogFX dialog = new DialogFX(DialogFX.Type.QUESTION);
        dialog.setTitleText(title);
        dialog.setMessage(text);

        dialog.addButtons(buttonLabels, ACCEPT, CANCEL);

        int value = dialog.showDialog();

        return value == ACCEPT;
    }
}
