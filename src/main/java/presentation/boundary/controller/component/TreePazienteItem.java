package presentation.boundary.controller.component;

import business.entity.Patologia;
import business.entity.Paziente;
import javafx.scene.control.Label;
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
        TreeChild item = new TreeChild(labelRubrica);

        if (!paziente.getNumeroCellulare().isEmpty()) {
            getChildren().add(item);
        }

        for (String numero : paziente.getNumeroCellulare()) {
            Label labelNumero = new Label(numero);
            TreeChild numeroItem = new TreeChild(labelNumero);
            item.getChildren().add(numeroItem);
        }

        TreePatologiaItem treePatologiaItem =
                new TreePatologiaItem(paziente.getPatologia(), "afflictedDiseases");

        getChildren().add(treePatologiaItem);
    }
}
