package business.transfer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BooleanBox<T> {
    public final T element;
    private BooleanProperty checked = new SimpleBooleanProperty(false);

    public BooleanBox(T element) {
        this.element = element;
    }

    public boolean isChecked() {
        return checked.get();
    }

    public void setChecked(boolean checked) {
        this.checked.setValue(checked);
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }
}
