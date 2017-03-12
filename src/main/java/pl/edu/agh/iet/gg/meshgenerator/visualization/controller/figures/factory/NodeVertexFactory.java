package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.factory;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.NodeVertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodeRadiusStrategy;

import java.util.function.Consumer;

/**
 * @author Wojciech Pachuta.
 */
public class NodeVertexFactory {
    private final NodePositioningStrategy nodePositioningStrategy;
    private final NodeRadiusStrategy nodeRadiusStrategy;
    private final Consumer<E> onEClick;
    private final Consumer<I> onIClick;

    public NodeVertexFactory(NodePositioningStrategy nodePositioningStrategy, NodeRadiusStrategy nodeSizeStrategy, Consumer<E> onEClick, Consumer<I> onIClick) {
        this.nodePositioningStrategy = nodePositioningStrategy;
        this.nodeRadiusStrategy = nodeSizeStrategy;
        this.onEClick = onEClick;
        this.onIClick = onIClick;
    }

    public NodeVertex getNodeVertex(Node node) {
        NodeVertex nv = new NodeVertex(nodeRadiusStrategy.getNodeRadius(node), nodePositioningStrategy.getPosition(node), node);

        nv.onMouseClickedProperty().setValue(event -> {
            if (node instanceof E) {
                onEClick.accept((E) node);
            } else if (node instanceof I) {
                onIClick.accept((I) node);
            }
        });

        return nv;
    }

}
