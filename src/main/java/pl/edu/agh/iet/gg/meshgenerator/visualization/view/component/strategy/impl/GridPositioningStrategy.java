package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodePositioningStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class GridPositioningStrategy implements NodePositioningStrategy {

    private static final double ROOT_Z_POSITION = Config.getDouble("component.vertex.RootZPosition");
    private static final double VERTICAL_EDGE_LENGTH = Config.getDouble("component.edge.vertical.InitialLength");
    private static final double HORIZONTAL_EDGE_LENGTH = Config.getDouble("component.edge.horizontal.InitialLength");


    @Override
    public double[] getPosition(Node node) {
        return new double[] {node.getOffsetX() * HORIZONTAL_EDGE_LENGTH, node.getOffsetY() * HORIZONTAL_EDGE_LENGTH,
                node.getLevel() * VERTICAL_EDGE_LENGTH - ROOT_Z_POSITION};
    }

}
