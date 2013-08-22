package presentation.boundary;

import javafx.stage.Modality;
import util.Parameter;
import util.SimpleLabelTranslator;

public class SchermataInserimentoPaziente extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissionePaziente";

    public SchermataInserimentoPaziente(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("addPatient"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
