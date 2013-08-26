package presentation.boundary.controller.component;

import business.entity.InterventoCompleto;
import business.entity.Operazione;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import utility.DateConverter;
import utility.SimpleLabelTranslator;

public class TreeInterventoCompletoItem extends TreeItem implements TreeChild {
    public TreeInterventoCompletoItem(InterventoCompleto intervento) {
        this.intervento = intervento;
        build();
    }

    public InterventoCompleto getIntervento() {
        return intervento;
    }

    private InterventoCompleto intervento;

    protected void build() {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("intervention", intervento.getId());
        setValue(rootLabel);

        insertChild("city", intervento.getCitta());
        insertChild("postalCode", intervento.getCap());
        insertChild("address", intervento.getIndirizzo());
        insertChild("date", intervento.getData().toString(DateConverter.NORMAL_DATE_FORMAT));
        insertChild("time", intervento.getOra().toString(DateConverter.EUROPEAN_TIME_FORMAT));

        getChildren().add(new TreePazienteItem(intervento.getPaziente()));
        getChildren().add(new TreeInfermiereItem(intervento.getInfermiere()));

        Label labelOperazione = new Label(SimpleLabelTranslator.translate("operations"));
        TreeItem item = new TreeItem(labelOperazione);

        if (!intervento.getOperazione().isEmpty()) {
            getChildren().add(item);
        }

        for (Operazione operazione : intervento.getOperazione()) {
            item.getChildren().add(new TreeOperazioneItem(operazione));
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
