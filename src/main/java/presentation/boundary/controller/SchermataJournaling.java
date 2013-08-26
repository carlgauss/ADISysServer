package presentation.boundary.controller;

import business.entity.Accelerometro;
import business.entity.GPS;
import business.entity.InterventoCompleto;
import business.entity.Journaling;
import business.transfer.JournalingFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import presentation.boundary.ReturnableStage;
import presentation.boundary.controller.component.TreeChild;
import presentation.boundary.controller.component.TreeInterventoCompletoItem;
import presentation.boundary.controller.itemfactory.DateDepictionFactory;
import presentation.boundary.controller.itemfactory.TimeDepictionFactory;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
import utility.Parameter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SchermataJournaling extends Schermata {
    private ReturnableStage stage;

    private FrontController fc = FrontControllerFactory.getFrontController();

    @FXML
    private Node root;
    @FXML
    private Labeled titolo;

    @FXML
    private ComboBox<JournalingFile> journaling;
    @FXML
    private Button carica;
    @FXML
    private TreeView intervento;

    @FXML
    private TableView<GPS> gps;
    @FXML
    private TableColumn<GPS, Double> latitudine;
    @FXML
    private TableColumn<GPS, Double> longitudine;

    @FXML
    private TableView<Accelerometro> accelerometro;
    @FXML
    private TableColumn<Accelerometro, Double> x;
    @FXML
    private TableColumn<Accelerometro, Double> y;
    @FXML
    private TableColumn<Accelerometro, Double> z;
    @FXML
    private TableColumn<Accelerometro, LocalDate> data;
    @FXML
    private TableColumn<Accelerometro, LocalTime> ora;

    @FXML
    private void onExit(ActionEvent event) {
        Stage stage = getStage();
        stage.close();
    }

    @FXML
    private void onAggiorna(ActionEvent event) {
        loadJournalingList();
    }

    @FXML
    private void onCarica(ActionEvent event) {
        int pos = journaling.getSelectionModel().getSelectedIndex();

        String journalingFileName = journaling.getItems().get(pos).getFileName();
        Parameter parameterJournaling = new Parameter();
        parameterJournaling.setValue("journaling", journalingFileName);

        Journaling journalingEntity = (Journaling) fc.processRequest("AnalizzaJournaling", parameterJournaling);
        populateJournaling(journalingEntity);
    }

    private static final double MAX_ACCELEROMETER_VALUE_WIDTH = 30;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadJournalingList();

        gps.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gps.setPlaceholder(new Label(BLANK));

        accelerometro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        accelerometro.setPlaceholder(new Label(BLANK));

        x.setMaxWidth(MAX_ACCELEROMETER_VALUE_WIDTH);
        y.setMaxWidth(MAX_ACCELEROMETER_VALUE_WIDTH);
        z.setMaxWidth(MAX_ACCELEROMETER_VALUE_WIDTH);

        x.setMinWidth(MAX_ACCELEROMETER_VALUE_WIDTH);
        y.setMinWidth(MAX_ACCELEROMETER_VALUE_WIDTH);
        z.setMinWidth(MAX_ACCELEROMETER_VALUE_WIDTH);

        intervento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (newValue instanceof TreeChild) {
                    TreeChild internalNode = (TreeChild) newValue;
                    TreeInterventoCompletoItem interventoCompleto =
                            (TreeInterventoCompletoItem) internalNode.getRootChild();

                    fillRilevation(interventoCompleto.getIntervento());
                }
            }
        });
    }

    private static final String BLANK = "";


    private ReturnableStage getStage() {
        return (ReturnableStage) root.getScene().getWindow();
    }

    private static final int FIRST = 0;

    private void loadJournalingList() {
        List<JournalingFile> fileList = (List<JournalingFile>) fc.processRequest("ElencaFileJournaling", null);
        ObservableList<JournalingFile> fileData = FXCollections.observableArrayList(fileList);

        if (fileData.size() > 0) {
            journaling.setDisable(false);
            carica.setDisable(false);

            journaling.setItems(fileData);
            journaling.getSelectionModel().select(FIRST);
        } else {
            journaling.setDisable(true);
            carica.setDisable(true);
        }
    }

    private void populateJournaling(Journaling journalingEntity) {
        List<InterventoCompleto> list = journalingEntity.getIntervento();

        TreeItem root = new TreeItem(null);
        intervento.setRoot(root);

        for (InterventoCompleto intervento : list) {
            TreeInterventoCompletoItem item = new TreeInterventoCompletoItem(intervento);
            item.setRootChild(true);
            root.getChildren().add(item);
        }
    }

    private void fillRilevation(InterventoCompleto interventoCompleto) {
        List<GPS> gpsList = interventoCompleto.getGps();
        ObservableList<GPS> gpsData = FXCollections.observableArrayList(gpsList);

        latitudine.setCellValueFactory(new PropertyValueFactory<GPS, Double>("latitudine"));
        longitudine.setCellValueFactory(new PropertyValueFactory<GPS, Double>("longitudine"));

        gps.setItems(gpsData);

        List<Accelerometro> accelerometroList = interventoCompleto.getAccelerometro();
        ObservableList<Accelerometro> accelerometroData = FXCollections.observableArrayList(accelerometroList);

        x.setCellValueFactory(new PropertyValueFactory<Accelerometro, Double>("x"));
        y.setCellValueFactory(new PropertyValueFactory<Accelerometro, Double>("y"));
        z.setCellValueFactory(new PropertyValueFactory<Accelerometro, Double>("z"));

        data.setCellValueFactory(new PropertyValueFactory<Accelerometro, LocalDate>("data"));
        data.setCellFactory(new DateDepictionFactory<Accelerometro>());

        ora.setCellValueFactory(new PropertyValueFactory<Accelerometro, LocalTime>("ora"));
        ora.setCellFactory(new TimeDepictionFactory<Accelerometro>());

        accelerometro.setItems(accelerometroData);
    }
}
