package presentation.boundary.controller.component;

import business.entity.Operazione;
import business.entity.ValoreRilevato;

public class TreeOperazioneItem extends TreeChild {
    public TreeOperazioneItem(Operazione operazione) {
        this.operazione = operazione;
        build();
    }

    private Operazione operazione;

    private static final String BLANK = "";
    private static final String COMMA = ", ";
    private static final String SEPARATOR = ": ";

    protected void build() {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("operation", operazione.getId());
        setValue(rootLabel);

        insertChild("name", operazione.getNome());

        ValoreRilevato valoreRilevato = operazione.getValoreRilevato();

        if (valoreRilevato != null) {
            if (!valoreRilevato.getMisura().equals(BLANK)) {
                insertChild("derivedValue", valoreRilevato.getMisura());
            }
            insertChild("elapsedTime", valoreRilevato.getTempoOperazione().toString());
        }

        if (!operazione.getNota().equals(BLANK)) {
            TranslatedCellNoteLabel cellLabel = new TranslatedCellNoteLabel("note", operazione.getNota());
            TreeChild item = new TreeChild(cellLabel);
            getChildren().add(item);
        }

        TreePatologiaItem treePatologiaItem =
                new TreePatologiaItem(operazione.getPatologia(), "curedDiseases");

        getChildren().add(treePatologiaItem);
    }
}
