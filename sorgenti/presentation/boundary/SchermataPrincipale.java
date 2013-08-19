package presentation.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.SimpleFormTranslator;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class SchermataPrincipale extends Application implements Boundary {
    @Override
    public void start(Stage stage) throws Exception {
        File file = new File("sorgenti/presentation/boundary/markup/language/italiano/italiano.properties");
        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("presentation.boundary.markup.language.italiano");
        Parent root = fxmlLoader.load(getClass().getResource("markup/SchermataPrincipale.fxml"));
        SimpleFormTranslator.translateAll(root, bundle);
        stage.setTitle(bundle.getString("adisysServer"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showWindow() {
        launch();
    }

    public static void main(String... args) {
        launch(args);
    }
}
