package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author Bartłomiej Grochal
 */
public class CenteredTextField extends TextField {

    public CenteredTextField() {
        super();
        initialize();
    }


    private void initialize() {
        GridPane.setColumnIndex(this, 1);

        setAlignment(Pos.CENTER);
    }

}
