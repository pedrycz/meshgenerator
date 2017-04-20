package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * @author Bartłomiej Grochal
 */
public class MenuGroup extends GridPane {

    public MenuGroup() {
        super();
        initialize();
    }


    private void initialize() {
        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPercentWidth(55);

        ColumnConstraints inputColumn = new ColumnConstraints();
        inputColumn.setPercentWidth(45);
        inputColumn.setHalignment(HPos.CENTER);

        getColumnConstraints().addAll(labelColumn, inputColumn);
        setPadding(new Insets(0, 0, 5, 0));
        setVgap(5);

        getChildren().add(new MenuSeparator());
    }

}
