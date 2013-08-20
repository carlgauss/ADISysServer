package util;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 20/08/13
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public class MessageDisplayerTest extends Application {
    @Test
    public void testShowMessage() throws Exception {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MessageDisplayer.showMessage("aTitle", "aText");
        System.out.println(MessageDisplayer.showConfirmMessage("aQuestion", "aTextQuestion"));
    }
}
