package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;

/**
 * @author Wojciech Pachuta.
 */
public interface NodeRadiusStrategy {
    double getNodeRadius(Node node);
}
