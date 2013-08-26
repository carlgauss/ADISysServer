package presentation.boundary.controller.component;

import business.entity.InterventoCompleto;
import business.entity.Operazione;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utility.DateConverter;
import utility.SimpleLabelTranslator;

public class TreeInterventoCompletoItem extends TreeChild {
    public TreeInterventoCompletoItem(InterventoCompleto intervento) {
        this.intervento = intervento;
        build();
    }

    public InterventoCompleto getIntervento() {
        return intervento;
    }

    private InterventoCompleto intervento;
    private static final double STATUS_SPACING = 12;

    protected void build() {
        TranslatedCellLabel rootLabel = new TranslatedCellLabel("intervention", intervento.getId());

        HBox statusBox = new HBox();

        statusBox.setSpacing(STATUS_SPACING);
        statusBox.getChildren().addAll(rootLabel);
        statusBox.getChildren().add(new GPSLabel(intervento.getGps()));
        statusBox.getChildren().add(new AccelerometroLabel(intervento.getAccelerometro()));

        setValue(statusBox);

        insertChild("city", intervento.getCitta());
        insertChild("postalCode", intervento.getCap());
        insertChild("address", intervento.getIndirizzo());
        insertChild("date", intervento.getData().toString(DateConverter.NORMAL_DATE_FORMAT));
        insertChild("time", intervento.getOra().toString(DateConverter.EUROPEAN_TIME_FORMAT));

        getChildren().add(new TreePazienteItem(intervento.getPaziente()));
        getChildren().add(new TreeInfermiereItem(intervento.getInfermiere()));

        Label labelOperazione = new Label(SimpleLabelTranslator.translate("operations"));
        TreeChild item = new TreeChild(labelOperazione);

        if (!intervento.getOperazione().isEmpty()) {
            getChildren().add(item);
        }

        for (Operazione operazione : intervento.getOperazione()) {
            item.getChildren().add(new TreeOperazioneItem(operazione));
        }
    }
}
