package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author Bartłomiej Grochal
 */
public class FullWidthButton extends Button {

    public FullWidthButton() {
        super();
        initialize();
    }


    private void initialize() {
        GridPane.setColumnIndex(this, 1);

        setMaxWidth(Double.MAX_VALUE);
    }

}
