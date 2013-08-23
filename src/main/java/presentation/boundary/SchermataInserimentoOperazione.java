package presentation.boundary;

import javafx.stage.Modality;
import utility.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataInserimentoOperazione extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneOperazione";

    public SchermataInserimentoOperazione(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("addOperation"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
