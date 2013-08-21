package presentation.boundary;

import javafx.stage.Modality;
import util.Parameter;
import util.SimpleLabelTranslator;

public class SchermataModificaInfermiere extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneInfermiere";

    public SchermataModificaInfermiere(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("editNurse"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
