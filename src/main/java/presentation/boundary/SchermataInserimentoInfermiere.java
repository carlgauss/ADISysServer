package presentation.boundary;

import javafx.stage.Modality;
import util.Parameter;
import util.SimpleLabelTranslator;

public class SchermataInserimentoInfermiere extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneInfermiere";

    public SchermataInserimentoInfermiere(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("addNurse"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}