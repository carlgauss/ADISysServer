package util;

import business.entity.Person;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.joda.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 19/08/13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class PersonValueFactory<Entity> implements Callback<TableColumn<Entity, Person>, TableCell<Entity, Person>> {
    @Override
    public TableCell<Entity, Person> call(TableColumn<Entity, Person> entityLocalDateTableColumn) {
        return new TableCell<Entity, Person>() {
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.getNome() + " " + item.getCognome());
                } else {
                    setText(null);
                }
            }

        };
    }
}
