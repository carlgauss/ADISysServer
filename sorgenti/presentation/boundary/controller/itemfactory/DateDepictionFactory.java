package presentation.boundary.controller.itemfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.joda.time.LocalDate;
import util.DateConverter;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 19/08/13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
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
