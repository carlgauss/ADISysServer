package presentation.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

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
        File file = new File("ADISysServer2/sorgenti/presentation/boundary/markup/aaa");
        System.out.println(file.exists());
        Parent root = FXMLLoader.load(getClass().getResource("sorgenti/presentation/boundary/markup/SchermataPrincipale.fxml"));

        //test
        stage.setTitle("Hello World");
        Scene scene = new Scene(root, 500, 575);


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
