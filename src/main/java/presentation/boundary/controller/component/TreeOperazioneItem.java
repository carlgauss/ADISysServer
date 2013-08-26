package presentation.boundary.controller.component;

import business.entity.Operazione;
import business.entity.ValoreRilevato;
import javafx.scene.control.TreeItem;

public class TreeOperazioneItem extends TreeItem implements TreeChild {
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
            TreeItem item = new TreeItem(cellLabel);
            getChildren().add(item);
        }
    }

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeItem item = new TreeItem(cellLabel);
        getChildren().add(item);
    }

    private boolean isRootChild;

    @Override
    public boolean isRootChild() {
        return isRootChild;
    }

    @Override
    public void setRootChild(boolean value) {
        isRootChild = value;
    }
}
