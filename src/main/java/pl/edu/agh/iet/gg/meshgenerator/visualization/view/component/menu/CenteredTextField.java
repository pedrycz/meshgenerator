package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bartłomiej Grochal
 */
public class CenteredTextField extends TextField {

    private static final Border INVALID_INPUT_BORDER =
            new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

    private BooleanProperty valid;


    public CenteredTextField() {
        super();
        initialize();
    }


    public void markInvalidInput() {
        setBorder(INVALID_INPUT_BORDER);
    }

    public void unmarkInvalidInput() {
        setBorder(Border.EMPTY);
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    public boolean isValid() {
        return valid.get();
    }

    public void setValid(boolean valid) {
        this.valid.set(valid);
    }


    private void initialize() {
        valid = new SimpleBooleanProperty(false);

        GridPane.setColumnIndex(this, 1);
        setAlignment(Pos.CENTER);
    }

}
