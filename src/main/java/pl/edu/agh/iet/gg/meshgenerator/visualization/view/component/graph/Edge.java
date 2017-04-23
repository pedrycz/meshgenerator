package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.geometry.Point3D;
import javafx.scene.shape.Cylinder;

/**
 * @author Bartłomiej Grochal
 */
public class Edge extends Cylinder {

    private final pl.edu.agh.iet.gg.meshgenerator.model.Edge edge;


    public Edge(double radius, double length, Point3D rotationAxis, double rotationAngle, double[] translations,
                pl.edu.agh.iet.gg.meshgenerator.model.Edge edge) {
        super(radius, length);

        this.edge = edge;
        initialize(rotationAxis, rotationAngle, translations);
    }


    public pl.edu.agh.iet.gg.meshgenerator.model.Edge getEdge() {
        return edge;
    }


    private void initialize(Point3D rotationAxis, double rotationAngle, double[] translations) {
        setRotationAxis(rotationAxis);
        setRotate(rotationAngle);
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);

        setMaterial(Constants.EDGE_MATERIAL);
    }

}
