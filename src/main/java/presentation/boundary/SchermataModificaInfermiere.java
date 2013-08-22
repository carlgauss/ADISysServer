package presentation.boundary;

import javafx.stage.Modality;
import utility.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataModificaInfermiere extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneInfermiere";

    public SchermataModificaInfermiere(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("editNurse"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
