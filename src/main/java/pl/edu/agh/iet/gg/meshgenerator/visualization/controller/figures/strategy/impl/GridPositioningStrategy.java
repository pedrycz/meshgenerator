package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodePositioningStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class GridPositioningStrategy implements NodePositioningStrategy {
    @Override
    public double[] getPosition(Node node) {
        return new double[] {node.getOffsetX() * 100, node.getOffsetY() * 100, node.getLevel() * 100 - 300};
    }
}
