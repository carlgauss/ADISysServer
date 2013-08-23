package presentation.boundary.controller.itemfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.joda.time.LocalTime;
import utility.DateConverter;


public class TimeDepictionFactory<Entity> implements Callback<TableColumn<Entity, LocalTime>, TableCell<Entity, LocalTime>> {
    @Override
    public TableCell<Entity, LocalTime> call(TableColumn<Entity, LocalTime> entityLocalDateTableColumn) {
        return new TableCell<Entity, LocalTime>() {
            protected void updateItem(LocalTime item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.toString(DateConverter.EUROPEAN_TIME_FORMAT));
                } else {
                    setText(null);
                }
            }

        };
    }
}
