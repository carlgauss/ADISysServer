package presentation.boundary;

import javafx.stage.Modality;
import utility.Parameter;
import utility.SimpleLabelTranslator;

public class SchermataInserimentoIntervento extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneIntervento";

    public SchermataInserimentoIntervento(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleLabelTranslator.translate("addIntervention"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
