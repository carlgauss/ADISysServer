package presentation.boundary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
import presentation.boundary.controller.Schermata;
import business.transfer.Parameter;
import utility.SimpleLabelTranslator;

import java.io.IOException;
import java.util.Properties;

public abstract class ReturnableStage extends Stage implements Boundary {
    private static final String MARKUP_FOLDER = "markup/";
    private static final String FXML_EXTENSION = ".fxml";

    protected Object value;
    protected Region root;
    protected double width;
    protected double height;
    protected Scene scene;

    public ReturnableStage(Parameter parameter, String schemeResource) {
        this(parameter, schemeResource, false);
    }

    public ReturnableStage(Parameter parameter, String schemeResource, boolean fullscreen) {

        Properties properties = SimpleLabelTranslator.getLanguage();

        Class<?> mainClass = getClass();

        schemeResource = MARKUP_FOLDER + schemeResource + FXML_EXTENSION;

        FXMLLoader fxmlLoader = new FXMLLoader(mainClass.getResource(schemeResource));

        try {
            root = (Region) fxmlLoader.load();
            Schermata schermata = fxmlLoader.getController();

            schermata.initData(parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleLabelTranslator.translateAll(root, properties);

        width = root.getPrefWidth();
        height = root.getPrefHeight();

        if (fullscreen) {
            width = Screen.getPrimary().getBounds().getWidth();
            height = Screen.getPrimary().getBounds().getHeight();
        }

        scene = new Scene(root, width, height);

        this.setMinWidth(width);
        this.setMinHeight(height);

        setScene(scene);
    }

    public ReturnableStage() {
    }

    public void setResult(Object object) {
        value = object;
    }

    @Override
    public Object showWindow(Parameter parameter) {
        showAndWait();
        return value;
    }
}