package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.geometry.Point3D;

/**
 * @author Bartłomiej Grochal
 */
public class VerticalEdge extends Edge {

    public VerticalEdge() {
        this(5, 170);      // It is necessary to subtract doubled radius of a sphere corresponding to a vertex.
    }

    public VerticalEdge(double radius, double length) {
        this(radius, length, new double[]{0, 0, 0});
    }

    public VerticalEdge(double radius, double length, double[] translations) {
        super(radius, length, 0, translations);

        setRotationAxis(new Point3D(1, 0, 0));
        setRotate(90);
    }

}
