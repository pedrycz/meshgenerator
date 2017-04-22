package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy;

import pl.edu.agh.iet.gg.meshgenerator.model.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Wojciech Pachuta.
 */
public interface EdgeRadiusStrategy {

    double EDGE_RADIUS = Config.getDouble("component.edge.Radius");


    double getEdgeRadius(Edge edge);

}
