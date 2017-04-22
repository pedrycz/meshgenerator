package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Wojciech Pachuta.
 */
public interface NodeRadiusStrategy {

    double VERTEX_RADIUS = Config.getDouble("component.vertex.Radius");


    double getNodeRadius(Node node);

}
