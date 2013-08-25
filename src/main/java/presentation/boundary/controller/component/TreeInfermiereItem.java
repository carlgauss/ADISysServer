package presentation.boundary.controller.component;

import business.entity.Infermiere;
import javafx.scene.control.TreeItem;

public class TreeInfermiereItem extends TreeItem {
    public TreeInfermiereItem(Infermiere infermiere) {
        this.infermiere = infermiere;
        build();
    }

    private Infermiere infermiere;

    protected void build() {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("nurse", infermiere.getId());
        setValue(rootLabel);

        insertChild("name", infermiere.getNome());
        insertChild("surname", infermiere.getCognome());
    }

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeItem item = new TreeItem(cellLabel);
        getChildren().add(item);
    }
}
