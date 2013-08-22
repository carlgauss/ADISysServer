package utility.dialogfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Heckler (mark.heckler@gmail.com, @HecklerMark)
 */
public final class DialogFX extends Stage {
    /**
     * Type of dialog box is one of the following, each with a distinct icon:
     * <p/>
     * ACCEPT = check mark icon
     * <p/>
     * ERROR = red 'X' icon
     * <p/>
     * INFO = blue 'i' (information) icon
     * <p/>
     * QUESTION = blue question mark icon
     * <p/>
     * If no type is specified in the constructor, the default is INFO.
     */
    public enum Type {
        ACCEPT, ERROR, INFO, QUESTION
    }

    ;

    private Type type;
    private Stage stage;
    private Scene scene;
    private BorderPane pane = new BorderPane();
    private ImageView icon = new ImageView();
    private Label message = new Label();
    private HBox buttonBox = new HBox(10);
    private List<String> buttonLabels;
    private int buttonCancel = -1;
    private int buttonCount = 0;
    private int buttonSelected = -1;
    private List<String> stylesheets = new ArrayList<>();

    /**
     * Default constructor for a DialogFX dialog box. Creates an INFO box.
     *
     * @see Type
     */
    public DialogFX() {
        initDialog(Type.INFO);
    }

    /**
     * Constructor for a DialogFX dialog box that accepts one of the enumerated
     * types listed above.
     *
     * @param t The type of DialogFX dialog box to create.
     * @see Type
     */
    public DialogFX(Type t) {
        initDialog(t);
    }

    /**
     * Public method used to add custom buttons to a DialogFX dialog.
     *
     * @param labels A list of String variables. While technically unlimited,
     *               usability makes the practical limit around three.
     */
    public void addButtons(List<String> labels) {
        addButtons(labels, -1, -1);
    }

    /**
     * Public method used to add custom buttons to a DialogFX dialog.
     * Additionally, default and cancel buttons are identified so user can
     * trigger them with the ENTER key (default) and ESCAPE (cancel).
     *
     * @param labels     A list of String variables. While technically unlimited,
     *                   usability makes the practical limit around three.
     * @param defaultBtn Position within the list of labels of the button to
     *                   designate as the default button.
     * @param cancelBtn  Position within the list of labels of the button to
     *                   designate as the cancel button.
     */
    public void addButtons(List<String> labels, int defaultBtn, int cancelBtn) {
        buttonLabels = labels;

        for (int i = 0; i < labels.size(); i++) {
            final Button btn = new Button(labels.get(i));

            btn.setDefaultButton(i == defaultBtn);
            btn.setCancelButton(i == cancelBtn);

            if (i == defaultBtn) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        btn.requestFocus();
                    }
                });
            }

            buttonCancel = cancelBtn;

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    buttonSelected = buttonLabels.indexOf(((Button) evt.getSource()).getText());
                    stage.close();
                }
            });
            buttonBox.getChildren().add(btn);
        }

        buttonBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(buttonBox, Pos.CENTER);
        BorderPane.setMargin(buttonBox, new Insets(5, 5, 5, 5));
        pane.setBottom(buttonBox);
        buttonCount = labels.size();
    }

    private void addOKButton() {
        List<String> labels = new ArrayList<>(1);
        labels.add("OK");

        addButtons(labels, 0, 0);
    }

    private void addYesNoButtons() {
        /*
         * No default or cancel buttons designated, by design.
         * Some cases would require the Yes button to be default & No to cancel,
         * while others would require the opposite. You as the developer can
         * assign default/cancel Yes/No buttons using the full addButtons()
         * method if required. You have the power!
         */
        List<String> labels = new ArrayList<>(2);
        labels.add("Yes");
        labels.add("No");

        addButtons(labels);
    }

    /**
     * Allows developer to add stylesheet for DialogFX dialog, supplementing or
     * overriding existing styling.
     *
     * @param stylesheet String variable containing the name or path/name
     *                   of the stylesheet to add to the dialog's scene and contained controls.
     */
    public void addStylesheet(String stylesheet) {
        //stylesheet = stylesheet;
        try {
            String newStyle = this.getClass().getResource(stylesheet).toExternalForm();
            stylesheets.add(newStyle);
        } catch (Exception ex) {
            System.err.println("Unable to find specified stylesheet: " + stylesheet);
            System.err.println("Error message: " + ex.getMessage());
        }
    }

    private void initDialog(Type t) {
        stage = new Stage();

        setType(t);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3);
    }

    private void loadIconFromResource(String fileName) {
        Image imgIcon = new Image(getClass().getResourceAsStream(fileName));
        icon.setPreserveRatio(true);
        icon.setFitHeight(48);
        icon.setImage(imgIcon);
    }

    /**
     * Sets the text displayed within the DialogFX dialog box. Word wrap ensures
     * that all text is displayed.
     *
     * @param msg String variable containing the text to display.
     */
    public void setMessage(String msg) {
        message.setText(msg);
        message.setWrapText(true);
    }

    /**
     * Sets the modality of the DialogFX dialog box.
     *
     * @param isModal Boolean. A true value = APPLICATION_MODAL, false = NONE.
     */
    public void setModal(boolean isModal) {
        stage.initModality((isModal ? Modality.APPLICATION_MODAL : Modality.NONE));
    }

    /**
     * Sets the text diplayed in the title bar of the DialogFX dialog box.
     *
     * @param title String containing the text to place in the title bar.
     */
    public void setTitleText(String title) {
        stage.setTitle(title);
    }

    /**
     * Sets the Type of DialogFX dialog box to display.
     *
     * @param typeToSet One of the supported types of dialogs.
     * @see Type
     */
    public void setType(Type typeToSet) {
        type = typeToSet;
    }

    private void populateStage() {
        String iconFile;

        switch (type) {
            case ACCEPT:
                iconFile = "Dialog-accept.png";
                addOKButton();
                break;
            case ERROR:
                iconFile = "Dialog-error.png";
                addOKButton();
                break;
            case INFO:
                iconFile = "Dialog-info.png";
                addOKButton();
                break;
            case QUESTION:
                iconFile = "Dialog-question.png";
                break;
            default:
                iconFile = "Dialog-info.png";
                break;
        }

        try {
            loadIconFromResource(iconFile);
        } catch (Exception ex) {
            System.err.println("Exception trying to load icon file: " + ex.getMessage());
        }

        BorderPane.setAlignment(icon, Pos.CENTER);
        BorderPane.setMargin(icon, new Insets(5, 5, 5, 5));
        pane.setLeft(icon);

        BorderPane.setAlignment(message, Pos.CENTER);
        BorderPane.setMargin(message, new Insets(5, 5, 5, 5));

        pane.setCenter(message);
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.setStyle("-fx-background-color: rgba(0,0,0,0.1)");

        scene = new Scene(pane);
        for (int i = 0; i < stylesheets.size(); i++) {
            try {
                scene.getStylesheets().add(stylesheets.get(i));
            } catch (Exception ex) {
                System.err.println("Unable to load specified stylesheet: " + stylesheets.get(i));
                System.err.println(ex.getMessage());
            }
        }
        stage.setScene(scene);
    }

    /**
     * Displays the DialogFX dialog box and waits for user input.
     *
     * @return The index of the button pressed.
     */
    public int showDialog() {
        populateStage();
        if (type == Type.QUESTION) {
            if (buttonCount == 0) {
                addYesNoButtons();
            }
        }

        stage.setResizable(false);
        stage.setIconified(false);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.showAndWait();
        return (buttonSelected == -1 ? buttonCancel : buttonSelected);
    }
}
