package presentation.boundary;

import javafx.stage.Modality;
import utility.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataJournaling extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataJournaling";

    public SchermataJournaling(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("deriveJournaling"));

        initModality(Modality.APPLICATION_MODAL);
    }
}
