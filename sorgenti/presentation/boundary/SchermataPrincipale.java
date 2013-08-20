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

import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class SchermataPrincipale extends Application implements Boundary {
    private static final String MARKUP_FOLDER = "markup/";
    private static final String FXML_EXTENSION = ".fxml";
    //private static final String STYLESHEET = "stylesheet/caspian.css";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();

        ResourceBundle bundle = SimpleFormTranslator.getLanguage();

        Class<?> mainClass = getClass();

        String schemePath = MARKUP_FOLDER + mainClass.getSimpleName() + FXML_EXTENSION;
        Parent root = fxmlLoader.load(mainClass.getResource(schemePath));

        SimpleFormTranslator.translateAll(root, bundle);

        stage.setTitle(SimpleFormTranslator.translate("adisysServer"));
        stage.initStyle(StageStyle.UNDECORATED);

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, width, height);
        //scene.getStylesheets().add(mainClass.getResource(STYLESHEET).toString());

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showWindow(Parameter parameter) {
        launch();
    }
}
