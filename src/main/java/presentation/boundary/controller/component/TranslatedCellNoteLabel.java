package presentation.boundary.controller.component;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utility.SimpleLabelTranslator;

public class TranslatedCellNoteLabel extends HBox {
    public TranslatedCellNoteLabel(String key, String value) {
        key = SimpleLabelTranslator.translate(key);
        build(key, value);
    }

    public TranslatedCellNoteLabel(String key, String separator, String value) {
        key = SimpleLabelTranslator.translate(key);
        build(key, separator, value);
    }

    private static final String SEPARATOR = ": ";

    protected void build(String key, String value) {
        build(key, SEPARATOR, value);
    }

    private static final double SPACING_SIZE = 0;
    private static final double NOTE_MAX_WIDTH = 300;

    protected void build(String key, String separator, String value) {
        setSpacing(SPACING_SIZE);

        Label keyLbl = new Label(key);
        Label separatorLbl = new Label(separator);
        Label valueLbl = new Label(value);

        valueLbl.setMaxWidth(NOTE_MAX_WIDTH);
        valueLbl.setWrapText(true);

        getChildren().addAll(keyLbl, separatorLbl, valueLbl);
    }
}
