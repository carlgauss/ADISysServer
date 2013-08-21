package presentation.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Parameter;
import util.SimpleFormTranslator;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */

//TODO
public class SchermataInserimentoInfermiere extends Application implements Boundary {
    private static final String MARKUP_FOLDER = "markup/";
    private static final String FXML_EXTENSION = ".fxml";
    private static final String SCHEME_RESOURCE = "SchermataImmissioneInfermiere";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Properties properties = SimpleFormTranslator.getLanguage();

        Class<?> mainClass = getClass();

        String schemePath = MARKUP_FOLDER + SCHEME_RESOURCE + FXML_EXTENSION;
        Parent root = fxmlLoader.load(mainClass.getResource(schemePath));

        SimpleFormTranslator.translateAll(root, properties);

        stage.setTitle(SimpleFormTranslator.translate("addNurseScreen"));
        stage.setResizable(false);

        double width = 400;
        double height = 260;

        Scene scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showWindow(Parameter parameter) {
        launch();
    }
}
