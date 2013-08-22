package presentation.boundary.controller.itemfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.joda.time.LocalDate;
import utility.DateConverter;


public class DateDepictionFactory<Entity> implements Callback<TableColumn<Entity, LocalDate>, TableCell<Entity, LocalDate>> {
    @Override
    public TableCell<Entity, LocalDate> call(TableColumn<Entity, LocalDate> entityLocalDateTableColumn) {
        return new TableCell<Entity, LocalDate>() {
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.toString(DateConverter.NORMAL_DATE_FORMAT));
                } else {
                    setText(null);
                }
            }

        };
    }
}
