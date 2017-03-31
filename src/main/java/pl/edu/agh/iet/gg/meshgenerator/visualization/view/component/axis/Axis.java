package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * TODO: Add arrowheads to axes.
 * TODO: Add labels to axes.
 *
 * @author Bartłomiej Grochal
 */
public abstract class Axis extends Box {

    static final double AXIS_LENGTH = 1000.0;
    static final double AXIS_SIZE = 1.0;


    public Axis(double xDimension, double yDimension, double zDimension, Color color) {
        super(xDimension, yDimension, zDimension);
        setMaterial(new PhongMaterial(color));
    }

}
