package presentation.boundary.controller.itemfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.List;


public class ListTableCountFactory<Entity> implements Callback<TableColumn<Entity, List<?>>, TableCell<Entity, List<?>>> {
    @Override
    public TableCell<Entity, List<?>> call(TableColumn<Entity, List<?>> entityLocalDateTableColumn) {
        return new TableCell<Entity, List<?>>() {
            protected void updateItem(List<?> item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.size() + "");
                } else {
                    setText(null);
                }
            }

        };
    }
}

