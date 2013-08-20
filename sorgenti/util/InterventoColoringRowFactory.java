package util;

import business.entity.Intervento;
import business.entity.Paziente;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.Callback;
import org.joda.time.*;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 19/08/13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class InterventoColoringRowFactory<AtomicType> implements Callback<TableColumn<Intervento, AtomicType>, TableCell<Intervento, AtomicType>> {

    private final static double WIDTH = Screen.getPrimary().getBounds().getWidth();
    private final static double HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private static Blend blush;
    private static ColorAdjust monochrome = new ColorAdjust();
    private static ColorInput red = new ColorInput();

    static {
        red.setPaint(Color.rgb(0xFF, 0xDD, 0xDD));
        red.setHeight(HEIGHT);
        red.setWidth(WIDTH);
        monochrome.setHue(0.85);
        monochrome.setSaturation(-0.5);
        monochrome.setBrightness(-0.3);
        monochrome.setContrast(0.3);
        blush = new Blend(
                BlendMode.DARKEN,
                monochrome,
                red
        );
    }

    @Override
    public TableCell<Intervento, AtomicType> call(TableColumn<Intervento, AtomicType> entityItem) {

        TableCell<Intervento, AtomicType> cell = new TableCell<Intervento, AtomicType>() {

            protected void updateItem(AtomicType item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.toString());
                    TableRow<Intervento> row = getTableRow();
                    Intervento intervento = row.getItem();
                    if(InterventoDurationEditChecker.checkInterventoEditable(intervento)) {
                        row.setEffect(blush);
                        row.setEditable(false);
                        row.setDisable(true);
                    }
                } else {
                    setText(null);
                }
            }
        };
        return cell;
    }

}
