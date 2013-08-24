package presentation.boundary.controller.itemfactory;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


public class BooleanCheckTableFactory<Entity> implements Callback<TableColumn<Entity, BooleanProperty>, TableCell<Entity, BooleanProperty>> {
    @Override
    public TableCell<Entity, BooleanProperty> call(TableColumn<Entity, BooleanProperty> entityLocalDateTableColumn) {
        return new TableCell<Entity, BooleanProperty>() {
            protected void updateItem(BooleanProperty aBoolean, boolean empty) {
                super.updateItem(aBoolean, empty);
                if (!empty) {
                    CheckBox checkBox = new CheckBox("");
                    checkBox.setAllowIndeterminate(false);
                    aBoolean.bind(checkBox.selectedProperty());
                    setGraphic(checkBox);
                    setAlignment(Pos.CENTER);
                } else {
                    setText(null);
                }
            }

        };
    }
}

