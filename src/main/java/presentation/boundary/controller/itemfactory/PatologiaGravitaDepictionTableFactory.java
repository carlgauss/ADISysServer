package presentation.boundary.controller.itemfactory;

import business.entity.Patologia;
import business.transfer.BooleanBox;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class PatologiaGravitaDepictionTableFactory implements Callback<TableColumn<BooleanBox<Patologia>, Patologia>, TableCell<BooleanBox<Patologia>, Patologia>> {
    @Override
    public TableCell<BooleanBox<Patologia>, Patologia> call(TableColumn<BooleanBox<Patologia>, Patologia> booleanBoxPatologiaTableColumn) {
        return new TableCell<BooleanBox<Patologia>, Patologia>() {
            @Override
            protected void updateItem(Patologia patologia, boolean isEmpty) {
                super.updateItem(patologia, isEmpty);
                if (!isEmpty) {
                    setText(String.valueOf(patologia.getGravita()));
                    setTextAlignment(TextAlignment.CENTER);
                    setAlignment(Pos.CENTER);
                } else {
                    setText(null);
                }
            }
        };
    }
}
