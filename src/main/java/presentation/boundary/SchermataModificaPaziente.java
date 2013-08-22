package presentation.boundary;

import javafx.stage.Modality;
import utility.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataModificaPaziente extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissionePaziente";

    public SchermataModificaPaziente(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("editPatient"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
