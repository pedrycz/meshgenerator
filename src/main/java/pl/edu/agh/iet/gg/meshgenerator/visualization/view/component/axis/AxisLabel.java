package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * @author Bartłomiej Grochal
 */
public class AxisLabel extends Label {

    public AxisLabel(String axisLabel) {
        this(axisLabel, new double[]{0.0, 0.0, 0.0});
    }

    public AxisLabel(String axisLabel, double[] translations) {
        super(axisLabel);
        initialize(translations);
    }


    private void initialize(double[] translations) {
        setFont(Font.font(30.0));

        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

}
