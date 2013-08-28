package presentation.boundary.controller.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class ChoiceBoxGravita extends ChoiceBox<Integer> {

    private static final double WIDTH = 38;

    public ChoiceBoxGravita() {
        super();

        Integer[] indexes = new Integer[] {1, 2, 3, 4, 5};

        ObservableList<Integer> indexData = FXCollections.observableArrayList(indexes);

        setItems(indexData);

        setMaxWidth(WIDTH);
        setMinWidth(WIDTH);

        getSelectionModel().selectFirst();
    }
}
