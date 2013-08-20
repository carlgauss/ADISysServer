package util;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFormTranslator {

    private static final String LANGUAGE_DIR = "presentation.boundary.markup.language.";

    private static final Class<?> LABELED_CLASS = Labeled.class;
    private static final Class<?> TABLE_VIEW = TableView.class;
    private static final Class<?> MENU_BUTTON = MenuButton.class;

    public static void translateAll(Node root, ResourceBundle bundle) {
        Set<Node> nodeSet = root.lookupAll("*");
        for (Node node : nodeSet) {
            Class<?> nodeClass = node.getClass();
            if (LABELED_CLASS.isAssignableFrom(nodeClass)) {
                if (MENU_BUTTON.isAssignableFrom(nodeClass)) {
                    MenuButton menuButton = (MenuButton) node;
                    ObservableList<MenuItem> menuItems = menuButton.getItems();
                    for (MenuItem item : menuItems) {
                        String text = item.getText();
                        if (bundle.containsKey(text)) {
                            String translatedText = bundle.getString(text);
                            item.setText(translatedText);
                        }
                    }
                }
                Labeled labelObject = (Labeled) node;
                String text = labelObject.getText();
                if (bundle.containsKey(text)) {
                    String translatedText = bundle.getString(text);
                    labelObject.setText(translatedText);
                }

            } else if (TABLE_VIEW.isAssignableFrom(nodeClass)) {
                TableView<?> tableView = (TableView<?>) node;
                ObservableList<? extends TableColumn<?, ?>> columns = tableView.getColumns();
                for (TableColumn column : columns) {
                    Node graphic = column.getGraphic();
                    if (LABELED_CLASS.isAssignableFrom(nodeClass)) {
                        Labeled labeled = (Labeled) graphic;
                        String text = labeled.getText();
                        if (bundle.containsKey(text)) {
                            String translatedText = bundle.getString(text);
                            labeled.setText(translatedText);
                        }
                    } else {
                        translateAll(graphic, bundle);
                    }
                }
            }
        }
    }

    private static final String DEFAULT_LANGUAGE = "italiano";
    private static final String LANGUAGE_KEY = "language";
    private static final String PROPERTIES_EXTENSION = ".properties";
    private static final String LANGUAGE_FILE = LANGUAGE_KEY + PROPERTIES_EXTENSION;
    private static final String TRANSLATED_TEXT_PATH = "sorgenti/presentation/boundary/markup/language/";

    public static ResourceBundle getLanguage() {
        File languageFile = new File(LANGUAGE_FILE);
        String language = null;
        if (languageFile.exists()) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(LANGUAGE_FILE));
                String extractedLanguage = properties.getProperty(LANGUAGE_KEY);
                File languageTranslatorFile = new File(TRANSLATED_TEXT_PATH + extractedLanguage + PROPERTIES_EXTENSION);
                if (languageTranslatorFile.exists()) {
                    language = extractedLanguage;
                } else {
                    createDefaultLanguageFile();
                    language = DEFAULT_LANGUAGE;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createDefaultLanguageFile();
            language = DEFAULT_LANGUAGE;
        }
        ResourceBundle bundle = ResourceBundle.getBundle(SimpleFormTranslator.LANGUAGE_DIR + language);
        settedBundle = bundle;
        return bundle;
    }

    private static ResourceBundle settedBundle;

    private static void createDefaultLanguageFile() {
        Properties properties = new Properties();
        properties.setProperty(LANGUAGE_KEY, DEFAULT_LANGUAGE);
        try {
            properties.store(new FileOutputStream(LANGUAGE_FILE), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLanguage(String language) {
        Properties properties = new Properties();
        properties.setProperty(LANGUAGE_KEY, language);
        try {
            properties.store(new FileOutputStream(LANGUAGE_FILE), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        settedBundle = null;
    }

    public static String translate(String text) {
        if(settedBundle == null) {
            settedBundle = getLanguage();
        }
        String translatedText = text;
        if (settedBundle.containsKey(text)) {
            translatedText = settedBundle.getString(text);
        }
        return translatedText;
    }
}
