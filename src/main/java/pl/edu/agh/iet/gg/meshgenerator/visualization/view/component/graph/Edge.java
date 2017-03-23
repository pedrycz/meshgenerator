package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.geometry.Point3D;
import javafx.scene.shape.Cylinder;

/**
 * @author Bartłomiej Grochal
 */
public class Edge extends Cylinder {

    /**
     * FXML-default constructor.
     */
    @SuppressWarnings("unused")
    public Edge() {
        this(5, 0);
    }

    public Edge(double radius, double length) {
        super(radius, length);
    }

    public Edge(double radius, double length, double rotate, double[] translations) {
        super(radius, length);

        setRotate(rotate);
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

    public Edge(double radius, double length, double lat, double lon, double[] translations) {
        super(radius, length);

        setRotationAxis(new Point3D(0, 0, 1));
        setRotate(lon);
        setRotationAxis(new Point3D(0, 1, 0));
        setRotate(lat);
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

}
