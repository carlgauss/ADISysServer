package presentation.boundary.controller.itemfactory;

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
public class PersonPortrayalFactory<Entity, AtomicPerson extends Person> implements Callback<TableColumn<Entity, AtomicPerson>, TableCell<Entity, AtomicPerson>> {
    @Override
    public TableCell<Entity, AtomicPerson> call(TableColumn<Entity, AtomicPerson> entityLocalDateTableColumn) {
        return new TableCell<Entity, AtomicPerson>() {
            protected void updateItem(AtomicPerson item, boolean empty) {
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
