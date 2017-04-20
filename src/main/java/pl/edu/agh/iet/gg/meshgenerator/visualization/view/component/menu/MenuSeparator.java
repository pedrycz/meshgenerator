package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

/**
 * @author Bartłomiej Grochal
 */
public class MenuSeparator extends Separator {

    public MenuSeparator() {
        super();
        initialize();
    }


    private void initialize() {
        GridPane.setColumnIndex(this, 0);
        GridPane.setColumnSpan(this, 2);
        GridPane.setRowIndex(this, 0);
    }

}
