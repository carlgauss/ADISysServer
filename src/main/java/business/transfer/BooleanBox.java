package business.transfer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BooleanBox<T> {
    private final T element;
    private BooleanProperty checked = new SimpleBooleanProperty(false);

    public BooleanBox(T element) {
        this.element = element;
    }

    public BooleanProperty getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.setValue(checked);
    }

    public T getElement() {
        return element;
    }
}
