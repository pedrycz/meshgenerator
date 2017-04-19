package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;

/**
 * @author Bartłomiej Grochal
 */
public class DecreasingEdgeRadiusStrategy implements EdgeRadiusStrategy {

    @Override
    public double getEdgeRadius(Edge edge) {
        return Math.pow(0.8, edge.getA().getLevel()) * 4;
    }

}
