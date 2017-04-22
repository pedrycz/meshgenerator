package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;

/**
 * @author Bartłomiej Grochal
 */
public class DecreasingNodeRadiusStrategy implements NodeRadiusStrategy {

    private static final double DECREASING_FACTOR = Config.getDouble("component.vertex.RadiusDecreasingFactor");


    @Override
    public double getNodeRadius(Node node) {
        return Math.pow(DECREASING_FACTOR, node.getLevel()) * VERTEX_RADIUS;
    }

}
