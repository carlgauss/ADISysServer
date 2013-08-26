package presentation.boundary.controller.component;

import business.entity.Infermiere;

public class TreeInfermiereItem extends TreeChild {
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
}
