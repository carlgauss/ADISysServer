package presentation.boundary.controller.component;

import business.entity.Operazione;
import business.entity.ValoreRilevato;
import javafx.scene.control.TreeItem;
import utility.SimpleLabelTranslator;

public class TreeOperazioneItem extends TreeItem<Operazione> {
    public TreeOperazioneItem(Operazione operazione) {
        super(operazione);
        this.operazione = operazione;
        build();
    }

    private Operazione operazione;
    private TreeItem root;

    private static final String BLANK = "";
    private static final String COMMA = ", ";
    private static final String SEPARATOR = ": ";

    protected void build() {
        String tempoIntervento = BLANK;

        if (operazione.getValoreRilevato() != null) {
            tempoIntervento = COMMA + SimpleLabelTranslator.translate("tempo") + SEPARATOR;
            tempoIntervento += operazione.getValoreRilevato().getTempoOperazione();
        }

        TranslatedCellLabel rootLabel = new TranslatedCellLabel("operation", operazione.getId() + tempoIntervento);
        setGraphic(rootLabel);

        insertChild("name", operazione.getNome());

        ValoreRilevato valoreRilevato = operazione.getValoreRilevato();

        if ((valoreRilevato != null) && (!valoreRilevato.getMisura().equals(BLANK))) {
            insertChild("derivedValue", valoreRilevato.getMisura());
        }

        if (!operazione.getNota().equals(BLANK)) {
            insertChild("note", operazione.getNota());
        }
    }

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeItem item = new TreeItem(cellLabel);
        root.getChildren().add(item);
    }
}
