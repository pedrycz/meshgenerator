package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.scene.shape.Sphere;

/**
 * @author Bartłomiej Grochal
 */
public class Vertex extends Sphere {

    public Vertex() {
        this(15.0, new double[]{0, 0, 0});
    }

    public Vertex(double radius, double[] translations) {
        super(radius);

        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

}
