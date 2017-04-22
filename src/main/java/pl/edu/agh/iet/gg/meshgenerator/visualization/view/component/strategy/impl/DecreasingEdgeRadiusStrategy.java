package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;

/**
 * @author Bartłomiej Grochal
 */
public class DecreasingEdgeRadiusStrategy implements EdgeRadiusStrategy {

    private static final double DECREASING_FACTOR = Config.getDouble("component.edge.RadiusDecreasingFactor");


    @Override
    public double getEdgeRadius(Edge edge) {
        return Math.pow(DECREASING_FACTOR, edge.getA().getLevel()) * EDGE_RADIUS;
    }

}
