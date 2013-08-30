package presentation.boundary;

import javafx.stage.Modality;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataPatologia extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataPatologia";

    public SchermataPatologia(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("manageDiseases"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
