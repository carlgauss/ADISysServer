package presentation.boundary.controller.component;

import javafx.scene.control.TextField;

public class IndexedTextField extends TextField {
    public final int i;

    public IndexedTextField(int i) {
        super();
        this.i = i;
        getStyleClass().add("transparent");
    }
}
