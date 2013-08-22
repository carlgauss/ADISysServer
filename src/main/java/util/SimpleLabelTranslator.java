package util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;


public class SimpleLabelTranslator {

    private static final Class<?> LABELED_CLASS = Labeled.class;
    private static final Class<?> TABLE_VIEW = TableView.class;
    private static final Class<?> MENU_BUTTON = MenuButton.class;

    public static void translateAll(Node root, Properties properties) {
        Set<Node> nodeSet = root.lookupAll("*");
        for (Node node : nodeSet) {
            Class<?> nodeClass = node.getClass();
            if (LABELED_CLASS.isAssignableFrom(nodeClass)) {
                if (MENU_BUTTON.isAssignableFrom(nodeClass)) {
                    MenuButton menuButton = (MenuButton) node;
                    ObservableList<MenuItem> menuItems = menuButton.getItems();
                    for (MenuItem item : menuItems) {
                        String text = item.getText();
                        if (properties.containsKey(text)) {
                            String translatedText = properties.getProperty(text);
                            item.setText(translatedText);
                        }
                    }
                }
                Labeled labelObject = (Labeled) node;
                String text = labelObject.getText();
                if (properties.containsKey(text)) {
                    String translatedText = properties.getProperty(text);
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
                        if (properties.containsKey(text)) {
                            String translatedText = properties.getProperty(text);
                            labeled.setText(translatedText);
                        }
                    } else {
                        translateAll(graphic, properties);
                    }
                }
            }
        }
    }

    private static final String DEFAULT_LANGUAGE = "italiano";
    private static final String LANGUAGE_KEY = "language";
    private static final String PROPERTIES_EXTENSION = ".properties";
    private static final String LANGUAGE_FILE = LANGUAGE_KEY + PROPERTIES_EXTENSION;
    private static final String TRANSLATED_TEXT_PATH = "language/";

    public static Properties getLanguage() {
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
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(TRANSLATED_TEXT_PATH + language + PROPERTIES_EXTENSION));
        } catch (IOException e) {
            e.printStackTrace();
        }
        settedProperties = properties;
        return properties;
    }

    private static Properties settedProperties;

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
        settedProperties = null;
    }

    public static String translate(String text) {
        if (settedProperties == null) {
            settedProperties = getLanguage();
        }
        String translatedText = text;
        if (settedProperties.containsKey(text)) {
            translatedText = settedProperties.getProperty(text);
        }
        return translatedText;
    }
}
