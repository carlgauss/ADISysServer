package presentation.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
public class SchermataPrincipale extends SimpleForm implements Boundary {
    @Override
    public void start(Stage stage) throws Exception {
        File file = new File("sorgenti/presentation/boundary/markup/language/italiano/italiano.properties");
        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("presentation.boundary.markup.language.italiano");
        Parent root = fxmlLoader.load(getClass().getResource("markup/SchermataPrincipale.fxml"));
        translateAll(root, bundle);
        stage.setTitle("Hello World");
        Scene scene = new Scene(root, 870, 660);
        stage.setFullScreen(true);


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
