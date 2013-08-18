package presentation.boundary;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.javafxdata.control.TableViewFactory;
import org.javafxdata.datasources.reader.DataSourceReader;

import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 18/08/13
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class SimpleForm extends Application {

    private static final Class<?> LABELED_CLASS = Labeled.class;
    private static final Class<?> TABLE_VIEW = TableView.class;

    protected static void translateAll(Node root, ResourceBundle bundle) {
        Set<Node> nodeSet = root.lookupAll("*");
        for(Node node : nodeSet) {
            Class<?> nodeClass = node.getClass();
            if (LABELED_CLASS.isAssignableFrom(nodeClass)) {
                Labeled labelObject = (Labeled) node;
                String text = labelObject.getText();
                if(bundle.containsKey(text)) {
                    String translatedText = bundle.getString(text);
                    labelObject.setText(translatedText);
                }
            } else if(TABLE_VIEW.isAssignableFrom(nodeClass)) {
                TableView<?> tableView = (TableView<?>) node;
                ObservableList<? extends TableColumn<?, ?>> columns = tableView.getColumns();
                for(TableColumn column : columns) {
                    System.out.println(columns.size());
                    Node graphic = column.getGraphic();
                    if(LABELED_CLASS.isAssignableFrom(nodeClass)) {
                        Labeled labeled = (Labeled) graphic;
                        String text = labeled.getText();
                        if(bundle.containsKey(text)) {
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
