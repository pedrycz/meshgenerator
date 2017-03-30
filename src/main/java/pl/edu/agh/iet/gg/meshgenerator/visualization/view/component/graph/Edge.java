package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.geometry.Point3D;
import javafx.scene.shape.Cylinder;

/**
 * @author Bartłomiej Grochal
 */
public class Edge extends Cylinder {

    public Edge(double radius, double length, Point3D rotationAxis, double rotationAngle, double[] translations) {
        super(radius, length);

        setRotationAxis(rotationAxis);
        setRotate(rotationAngle);
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

}
