package presentation.boundary.controller.component;

import business.entity.InterventoCompleto;
import business.entity.Operazione;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import utility.DateConverter;
import utility.SimpleLabelTranslator;

public class TreeInterventoCompletoItem extends TreeItem<InterventoCompleto> {
    public TreeInterventoCompletoItem(InterventoCompleto intervento) {
        super(intervento);
        this.intervento = intervento;
        build();
    }

    private InterventoCompleto intervento;
    private TreeItem root;

    protected void build() {
        //TODO

        TranslatedCellLabel rootLabel = new TranslatedCellLabel("intervention", intervento.getId());
        setGraphic(rootLabel);

        insertChild("city", intervento.getCitta());
        insertChild("postalCode", intervento.getCap());
        insertChild("address", intervento.getIndirizzo());
        insertChild("date", intervento.getData().toString(DateConverter.NORMAL_DATE_FORMAT));
        insertChild("time", intervento.getOra().toString(DateConverter.EUROPEAN_TIME_FORMAT));

        root.getChildren().add(new TreePazienteItem(intervento.getPaziente()));
        root.getChildren().add(new TreeInfermiereItem(intervento.getInfermiere()));

        Label labelOperazione = new Label(SimpleLabelTranslator.translate("operations"));
        TreeItem item = new TreeItem(labelOperazione);

        if (!intervento.getOperazione().isEmpty()) {
            root.getChildren().add(item);
        }

        for (Operazione operazione : intervento.getOperazione()) {
            item.getChildren().add(new TreeOperazioneItem(operazione));
        }
    }

    protected void insertChild(String key, String value) {
        TranslatedCellLabel cellLabel = new TranslatedCellLabel(key, value);
        TreeItem item = new TreeItem(cellLabel);
        root.getChildren().add(item);
    }
}
