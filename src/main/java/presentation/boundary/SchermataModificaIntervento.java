package presentation.boundary;

import javafx.stage.Modality;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataModificaIntervento extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneIntervento";

    public SchermataModificaIntervento(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("editIntervention"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
