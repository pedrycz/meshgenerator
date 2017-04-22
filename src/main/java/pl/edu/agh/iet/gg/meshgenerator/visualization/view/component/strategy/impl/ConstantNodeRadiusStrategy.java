package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class ConstantNodeRadiusStrategy implements NodeRadiusStrategy {

    @Override
    public double getNodeRadius(Node node) {
        return VERTEX_RADIUS;
    }

}
