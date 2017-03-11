package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.scene.shape.Box;

/**
 * TODO: Add axes' colors if necessary.
 * TODO: Add arrowheads to axes.
 * TODO: Add labels to axes.
 *
 * @author Bartłomiej Grochal
 */
public abstract class Axis extends Box {

    protected static final double AXIS_LENGTH = 1000.0;
    protected static final double AXIS_SIZE = 1.0;


    public Axis(double xDimension, double yDimension, double zDimension) {
        super(xDimension, yDimension, zDimension);
    }

}
