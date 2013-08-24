package presentation.boundary.controller.itemfactory;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;


public class ListTableCountFactory implements Callback<ListView<List<?>>, ListCell<List<?>>> {

    @Override
    public ListCell<List<?>> call(ListView<List<?>> entityListView) {
        return new ListCell<List<?>>() {
            @Override
            protected void updateItem(List<?> list, boolean empty) {
                super.updateItem(list, empty);
                if (!empty) {
                    setText(list.size() + "");
                } else {
                    setText(null);
                }
            }
        };
    }
}

