package presentation.boundary.controller.component;

import business.entity.Paziente;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import utility.DateConverter;
import utility.SimpleLabelTranslator;

public class TreePazienteItem extends TreeChild {
    public TreePazienteItem(Paziente paziente) {
        this.paziente = paziente;
        build();
    }

    private Paziente paziente;

    protected void build() {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("patient", paziente.getId());
        setValue(rootLabel);

        insertChild("name", paziente.getNome());
        insertChild("surname", paziente.getCognome());
        insertChild("birthDate", paziente.getData().toString(DateConverter.NORMAL_DATE_FORMAT));

        Label labelRubrica = new Label(SimpleLabelTranslator.translate("phoneBook"));
        TreeItem item = new TreeItem(labelRubrica);

        if (!paziente.getNumeroCellulare().isEmpty()) {
            getChildren().add(item);
        }

        for (String numero : paziente.getNumeroCellulare()) {
            Label labelNumero = new Label(numero);
            TreeItem numeroItem = new TreeItem(labelNumero);
            item.getChildren().add(numeroItem);
        }
    }

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeItem item = new TreeItem(cellLabel);
        getChildren().add(item);
    }
}
