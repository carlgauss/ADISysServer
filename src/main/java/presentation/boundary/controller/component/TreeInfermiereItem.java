package presentation.boundary.controller.component;

import business.entity.Infermiere;
import javafx.scene.control.TreeItem;

public class TreeInfermiereItem extends TreeItem<Infermiere> {
    public TreeInfermiereItem(Infermiere infermiere) {
        super(infermiere);
        this.infermiere = infermiere;
        build();
    }

    private Infermiere infermiere;
    private TreeItem root;

    protected void build() {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("nurse", infermiere.getId());
        setGraphic(rootLabel);

        insertChild("name", infermiere.getNome());
        insertChild("surname", infermiere.getCognome());
    }

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeItem item = new TreeItem(cellLabel);
        root.getChildren().add(item);
    }
}
