package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * @author Bartłomiej Grochal
 */
public class MenuItemLabel extends Label {

    public MenuItemLabel() {
        super();
        initialize();
    }


    private void initialize() {
        GridPane.setColumnIndex(this, 0);
    }

}
