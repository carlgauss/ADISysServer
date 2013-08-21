package presentation.boundary;

import javafx.stage.Modality;
import util.Parameter;
import util.SimpleFormTranslator;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */

//TODO
public class SchermataInserimentoInfermiere extends ReturnableStage {
    private static final String SCHEME_RESOURCE = "SchermataImmissioneInfermiere";

    public SchermataInserimentoInfermiere(Parameter parameter) {
        super(parameter, SCHEME_RESOURCE);

        setTitle(SimpleFormTranslator.translate("addNurseScreen"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
    }
}
