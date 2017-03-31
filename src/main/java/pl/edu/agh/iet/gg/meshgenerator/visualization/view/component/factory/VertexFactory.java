package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;

/**
 * @author Wojciech Pachuta.
 */
public class VertexFactory implements ComponentFactory {

    private final NodePositioningStrategy nodePositioningStrategy;
    private final NodeRadiusStrategy nodeRadiusStrategy;


    public VertexFactory(NodePositioningStrategy nodePositioningStrategy, NodeRadiusStrategy nodeSizeStrategy) {
        this.nodePositioningStrategy = nodePositioningStrategy;
        this.nodeRadiusStrategy = nodeSizeStrategy;
    }


    public Vertex getVertex(Node node) {
        return new Vertex(nodeRadiusStrategy.getNodeRadius(node), nodePositioningStrategy.getPosition(node), node);
    }

}
