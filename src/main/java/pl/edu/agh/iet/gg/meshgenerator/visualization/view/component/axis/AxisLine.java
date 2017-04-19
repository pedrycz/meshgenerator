package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import static pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis.Axis.AXIS_LINE_LENGTH;
import static pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis.Axis.AXIS_LINE_SIZE;

/**
 * @author Bartłomiej Grochal
 */
public class AxisLine extends Box {

    public AxisLine() {
        this(new PhongMaterial());
    }

    public AxisLine(Material material) {
        super(AXIS_LINE_SIZE, AXIS_LINE_LENGTH, AXIS_LINE_SIZE);
        initialize(material);
    }


    private void initialize(Material material) {
        setMaterial(material);
    }

}
