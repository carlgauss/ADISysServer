package presentation.boundary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import util.Parameter;
import util.SimpleFormTranslator;

import java.io.IOException;
import java.util.Properties;

public abstract class ReturnableStage extends Stage implements Boundary {
    private static final String MARKUP_FOLDER = "markup/";
    private static final String FXML_EXTENSION = ".fxml";

    protected Parameter parameter;
    protected Object value;
    protected Region root;
    protected double width;
    protected double height;
    protected Scene scene;

    public ReturnableStage(Parameter parameter, String schemeResource) {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Properties properties = SimpleFormTranslator.getLanguage();

        Class<?> mainClass = getClass();

        schemeResource = MARKUP_FOLDER + schemeResource + FXML_EXTENSION;

        try {
            root = fxmlLoader.load(mainClass.getResource(schemeResource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleFormTranslator.translateAll(root, properties);

        width = root.getPrefWidth();
        height = root.getPrefHeight();

        scene = new Scene(root, width, height);
        setScene(scene);

        this.parameter = parameter;
    }

    public ReturnableStage() {
    }

    public void setResult(Object object) {
        value = object;
    }

    public Parameter getParameter() {
        return parameter;
    }

    @Override
    public Object showWindow(Parameter parameter) {
        showAndWait();
        return value;
    }
}