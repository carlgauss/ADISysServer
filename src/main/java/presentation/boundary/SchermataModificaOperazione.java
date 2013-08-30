package presentation.boundary;

import javafx.stage.Modality;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataModificaOperazione extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneOperazione";

    public SchermataModificaOperazione(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("editOperation"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
