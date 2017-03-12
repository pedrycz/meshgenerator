package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.factory;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.EdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;

/**
 * @author Wojciech Pachuta.
 */
public class EdgeFactory {
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

        double r = Math.sqrt(dx * dx + dy * dy + dz * dz);
        double lat = Math.acos(dz / r) / Math.PI * 180 - 90;
        double lon = - Math.acos(dx / Math.sqrt(dx * dx + dy * dy)) * (dy < 0 ? -1 : 1) / Math.PI * 180;

        Edge e = new Edge(edgeRadiusStrategy.getEdgeRadius(edge), r);

        e.getTransforms().add(new Rotate(90, Rotate.Z_AXIS));
//        e.getTransforms().add(new Rotate(lat, Rotate.Y_AXIS));
//        e.getTransforms().add(new Rotate(lon, Rotate.Z_AXIS));
//        e.getTransforms().add(new Translate(avg(n1p[0], n2p[0]), avg(n1p[1], n2p[1]), -avg(n1p[2], n2p[2])));

        e.getTransforms().add(new Rotate(lat, Rotate.Y_AXIS));
        e.getTransforms().add(new Rotate(lon, Rotate.Z_AXIS));
        e.getTransforms().add(new Translate(- r / 2,0,0));

        return e;
    }

    private static double avg(double a, double b) {
        return (a + b) / 2;
    }
}
