package presentation.boundary.controller.component;

import javafx.scene.control.Label;
import utility.SimpleLabelTranslator;

public class TranslatedLabel extends Label {
    public TranslatedLabel(String text) {
        text = SimpleLabelTranslator.translate(text);
        setText(text);
    }
}
