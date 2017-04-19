package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;

/**
 * @author Bartłomiej Grochal
 */
public class DecreasingNodeRadiusStrategy implements NodeRadiusStrategy {

    @Override
    public double getNodeRadius(Node node) {
        return Math.pow(0.8, node.getLevel()) * 15;
    }

}
