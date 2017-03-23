package pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;

/**
 * @author Wojciech Pachuta.
 */
public class NodeVertex extends Vertex {
    private final Node node;

    public NodeVertex(double radius, double[] translations, Node node) {
        super(radius, translations);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }


}
