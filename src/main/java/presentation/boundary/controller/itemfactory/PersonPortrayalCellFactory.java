package presentation.boundary.controller.itemfactory;

import business.entity.Person;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class PersonPortrayalCellFactory<Entity extends Person> implements Callback<ListView<Entity>, ListCell<Entity>> {

    @Override
    public ListCell<Entity> call(ListView<Entity> entityListView) {
        return new ListCell<Entity>() {
            @Override
            protected void updateItem(Entity entity, boolean empty) {
                super.updateItem(entity, empty);
                if (!empty) {
                    setText(entity.getNome() + " " + entity.getCognome());
                } else {
                    setText(null);
                }
            }
        };
    }
}

