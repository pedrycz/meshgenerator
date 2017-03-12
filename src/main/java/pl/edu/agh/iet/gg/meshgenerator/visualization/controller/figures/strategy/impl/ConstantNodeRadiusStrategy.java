package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodeRadiusStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class ConstantNodeRadiusStrategy implements NodeRadiusStrategy {
    @Override
    public double getNodeRadius(Node node) {
        return 15.0;
    }
}
