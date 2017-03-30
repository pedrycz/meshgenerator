package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory;

import javafx.geometry.Point3D;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodePositioningStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class EdgeFactory implements ComponentFactory {
    private final EdgeRadiusStrategy edgeRadiusStrategy;
    private final NodePositioningStrategy nodePositioningStrategy;

    public EdgeFactory(EdgeRadiusStrategy edgeRadiusStrategy, NodePositioningStrategy nodePositioningStrategy) {
        this.edgeRadiusStrategy = edgeRadiusStrategy;
        this.nodePositioningStrategy = nodePositioningStrategy;
    }

    public Edge getEdge(pl.edu.agh.iet.gg.meshgenerator.model.Edge edge) {
        double[] n1p = nodePositioningStrategy.getPosition(edge.getA());
        double[] n2p = nodePositioningStrategy.getPosition(edge.getB());

        double dx = n2p[0] - n1p[0];
        double dy = n2p[1] - n1p[1];
        double dz = n2p[2] - n1p[2];
        double length = Math.sqrt(dx * dx + dy * dy + dz * dz);

        Edge e = new Edge(edgeRadiusStrategy.getEdgeRadius(edge), length);

        //https://www.physicsforums.com/threads/calculating-axis-of-rotation.609724/
        Point3D axis = new Point3D(-length * dz, 0, length * dx);
        double angle = Math.acos(dy / Math.sqrt(dx * dx + dy * dy + dz * dz)) * 180 / Math.PI;

        e.rotationAxisProperty().setValue(axis);
        e.rotateProperty().setValue(-angle);
        e.translateXProperty().setValue(avg(n1p[0], n2p[0]));
        e.translateYProperty().setValue(avg(n1p[1], n2p[1]));
        e.translateZProperty().setValue(avg(n1p[2], n2p[2]));

        return e;
    }

    private static double avg(double a, double b) {
        return (a + b) / 2;
    }
}
