package presentation.boundary.controller.itemfactory;

import business.entity.Patologia;
import business.transfer.BooleanBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class PatologiaPartialDepictionTableFactory implements Callback<TableColumn<BooleanBox<Patologia>, Patologia>, TableCell<BooleanBox<Patologia>, Patologia>> {
    @Override
    public TableCell<BooleanBox<Patologia>, Patologia> call(TableColumn<BooleanBox<Patologia>, Patologia> booleanBoxPatologiaTableColumn) {
        return new TableCell<BooleanBox<Patologia>, Patologia>() {
            @Override
            protected void updateItem(Patologia patologia, boolean isEmpty) {
                super.updateItem(patologia, isEmpty);
                if (!isEmpty) {
                    setText(patologia.getCodice() + " " + patologia.getNome());
                } else {
                    setText(null);
                }
            }
        };
    }
}
