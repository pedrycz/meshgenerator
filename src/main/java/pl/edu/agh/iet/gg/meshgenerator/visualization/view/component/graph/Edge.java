package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

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
        this(radius, length, 0, new double[]{0, 0, 0});
    }

    public Edge(double radius, double length, double rotate, double[] translations) {
        super(radius, length);

        setRotate(rotate);
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

}
