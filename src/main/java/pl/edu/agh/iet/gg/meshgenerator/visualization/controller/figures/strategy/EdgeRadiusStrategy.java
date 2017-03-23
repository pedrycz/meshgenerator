package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy;

import pl.edu.agh.iet.gg.meshgenerator.model.Edge;

/**
 * @author Wojciech Pachuta.
 */
public interface EdgeRadiusStrategy {
    double getEdgeRadius(Edge edge);
}
