package presentation.boundary;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import util.Parameter;
import util.SimpleFormTranslator;

import java.io.IOException;
import java.util.Properties;

public abstract class ReturnableStage extends Stage implements Boundary {
    protected Parameter parameter;
    protected Object value;
    protected Region root;
    protected double width;
    protected double height;

    public ReturnableStage(Parameter parameter, String schemePath) {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Properties properties = SimpleFormTranslator.getLanguage();

        Class<?> mainClass = getClass();

        try {
            root = fxmlLoader.load(mainClass.getResource(schemePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleFormTranslator.translateAll(root, properties);

        width = root.getPrefWidth();
        height = root.getPrefHeight();

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