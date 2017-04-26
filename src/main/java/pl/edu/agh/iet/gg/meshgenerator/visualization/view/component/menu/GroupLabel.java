package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * @author Bartłomiej Grochal
 */
public class GroupLabel extends Label {

    public GroupLabel() {
        super();
        initialize();
    }


    private void initialize() {
        GridPane.setColumnIndex(this, 0);
        GridPane.setColumnSpan(this, 2);
        GridPane.setRowIndex(this, 1);

        setPadding(new Insets(0, 0, 3, 0));
        setStyle("-fx-font-weight: bold;");
    }

}
