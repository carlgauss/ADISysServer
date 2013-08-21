package presentation.boundary;

import javafx.scene.Scene;
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
    private static final String MARKUP_FOLDER = "markup/";
    private static final String FXML_EXTENSION = ".fxml";
    private static final String SCHEME_RESOURCE = "SchermataImmissioneInfermiere";

    private static final String SCHEME_PATH = MARKUP_FOLDER + SCHEME_RESOURCE + FXML_EXTENSION;

    public SchermataInserimentoInfermiere(Parameter parameter) {
        super(parameter, SCHEME_PATH);

        setTitle(SimpleFormTranslator.translate("addNurseScreen"));
        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, width, height);

        setScene(scene);
    }
}
