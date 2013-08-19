package util;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;

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
}
