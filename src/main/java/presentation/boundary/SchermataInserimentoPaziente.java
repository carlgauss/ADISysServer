package presentation.boundary;

import javafx.stage.Modality;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataInserimentoPaziente extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissionePaziente";

    public SchermataInserimentoPaziente(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("addPatient"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
