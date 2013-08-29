package presentation.boundary.controller.component;

import business.entity.Patologia;
import javafx.scene.control.Label;
import utility.SimpleLabelTranslator;

import java.util.List;

public class TreePatologiaItem extends TreeChild {
    public TreePatologiaItem(List<Patologia> patologiaList, String title) {
        this.patologiaList = patologiaList;
        this.title = SimpleLabelTranslator.translate(title);
        build();
    }

    private List<Patologia> patologiaList;
    private String title;

    private static final String BLANK = "";
    private static final String COMMA = ", ";
    private static final String SEPARATOR = ": ";

    protected void build() {
        Label label = new Label(title);
        setValue(label);

        for (Patologia patologia : patologiaList) {
            TreeChild treeChild = new TreeChild();
            buildSinglePatologia(patologia, treeChild);
            getChildren().add(treeChild);
        }
    }

    private void buildSinglePatologia(Patologia patologia, TreeChild treeChild) {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("disease", patologia.getCodice());
        treeChild.setValue(rootLabel);

        treeChild.insertChild("name", patologia.getNome());
        treeChild.insertChild("import", String.valueOf(patologia.getGravita()));
    }
}
