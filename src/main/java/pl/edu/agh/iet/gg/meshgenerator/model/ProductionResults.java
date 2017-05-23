package pl.edu.agh.iet.gg.meshgenerator.model;

import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.MainWindowController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Wojciech Pachuta.
 */
public class ProductionResults {

    private final List<Edge> addedEdges;
    private final List<Node> addedNodes;

    public ProductionResults(List<Node> addedNodes, List<Edge> addedEdges) {
        this.addedEdges = addedEdges;
        this.addedNodes = addedNodes;
    }

    public ProductionResults(List<Edge> addedEdges) {
        this.addedEdges = addedEdges;
        this.addedNodes = Collections.emptyList();
    }

    public List<Edge> getAddedEdges() {
        return Collections.unmodifiableList(addedEdges);
    }

    public List<Node> getAddedNodes() {
        return Collections.unmodifiableList(addedNodes);
    }

    public static ProductionResults merge(ProductionResults... productionChanges) {
        List<Edge> edges = Arrays.stream(productionChanges)
                .map(ProductionResults::getAddedEdges)
                .reduce(new ArrayList<>(), (acc, list) -> {
                    acc.addAll(list);
                    return acc;
                });

        List<Node> nodes = Arrays.stream(productionChanges)
                .map(ProductionResults::getAddedNodes)
                .reduce(new ArrayList<>(), (acc, list) -> {
                    acc.addAll(list);
                    return acc;
                });

        return new ProductionResults(nodes, edges);
    }
}
