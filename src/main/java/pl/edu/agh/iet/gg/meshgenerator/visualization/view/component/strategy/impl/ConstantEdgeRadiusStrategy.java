package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class ConstantEdgeRadiusStrategy implements EdgeRadiusStrategy {

    @Override
    public double getEdgeRadius(Edge edge) {
        return 7;
    }

}
