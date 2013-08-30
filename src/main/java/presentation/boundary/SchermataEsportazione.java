package presentation.boundary;

import javafx.stage.Modality;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataEsportazione extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataEsportazione";

    public SchermataEsportazione(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("exportPlanification"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
