package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.ProductionResults;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.factory.EdgeFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.factory.NodeVertexFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.EdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.NodeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.impl.ConstantEdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.impl.ConstantNodeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.figures.strategy.impl.GridPositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.VerticalEdge;

import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

/**
 * @author Bartłomiej Grochal
 */
public class MainWindowController {

    @FXML private RotatableGroup environmentGroup;
    @FXML private Group dynamicGraphGroup;

    private NodeVertexFactory nodeVertexFactory;
    private EdgeFactory edgeFactory;
    private Graph graph;

    @FXML
    @SuppressWarnings("unused")
    private void initialize() {

        NodePositioningStrategy nodePositioningStrategy = new GridPositioningStrategy();
        NodeRadiusStrategy nodeRadiusStrategy = new ConstantNodeRadiusStrategy();
        EdgeRadiusStrategy edgeRadiusStrategy = new ConstantEdgeRadiusStrategy();

        Consumer<E> onEClick = e -> {
            if(e.canApplyP1()) {
                ProductionResults pr = e.applyP1();
                dynamicGraphGroup.getChildren().addAll(pr.getAddedNodes().stream().map(nodeVertexFactory::getNodeVertex).collect(toList()));
                dynamicGraphGroup.getChildren().addAll(pr.getAddedEdges().stream().map(edgeFactory::getEdge).collect(toList()));
            }
        };

        Consumer<I> onIClick = i -> {
            if (i.canApplyP2a(i.getNW(), i.getNE())){
                ProductionResults pr = i.applyP2a(i.getNW(), i.getNE());
                dynamicGraphGroup.getChildren().addAll(pr.getAddedNodes().stream().map(nodeVertexFactory::getNodeVertex).collect(toList()));
                dynamicGraphGroup.getChildren().addAll(pr.getAddedEdges().stream().map(edgeFactory::getEdge).collect(toList()));
            }
        };

        this.nodeVertexFactory = new NodeVertexFactory(nodePositioningStrategy, nodeRadiusStrategy, onEClick, onIClick);
        this.edgeFactory = new EdgeFactory(edgeRadiusStrategy, nodePositioningStrategy);

        this.graph = new Graph();

        environmentGroup.rx.setAngle(-45);
        environmentGroup.ry.setAngle(-45);
        environmentGroup.rz.setAngle(-35);

        initializeGraph();
    }

    private void initializeGraph() {
        Node root = nodeVertexFactory.getNodeVertex(graph.getRoot());
        dynamicGraphGroup.getChildren().add(root);
    }


    private void createDynamicGraphExample() {
        Group secondLevelGroup = new Group();
        secondLevelGroup.getChildren().addAll(
                new Edge(5, 200, 0, new double[]{-100, 0, 0}),
                new Edge(5, 200, 90, new double[]{0, 100, 0}),
                new Edge(5, 200, 0, new double[]{100, 0, 0}),
                new Edge(5, 200, 90, new double[]{0, -100, 0}),

                new Edge(5, 141.42, 135, new double[]{-50, -50, 0}),
                new Edge(5, 141.42, 45, new double[]{-50, 50, 0}),
                new Edge(5, 141.42, 135, new double[]{50, 50, 0}),
                new Edge(5, 141.42, 45, new double[]{50, -50, 0}),

                new Vertex(15, new double[]{0, 0, 0}),
                new Vertex(15, new double[]{-100, -100, 0}),
                new Vertex(15, new double[]{-100, 100, 0}),
                new Vertex(15, new double[]{100, -100, 0}),
                new Vertex(15, new double[]{100, 100, 0})
        );

        Group firstToSecondLevelGroup = new Group();
        firstToSecondLevelGroup.getChildren().add(new VerticalEdge(5, 170, new double[]{0, 0, -100}));

        Group firstLevelGroup = new Group();
        firstLevelGroup.getChildren().add(new Vertex(15, new double[]{0, 0, -200}));

        dynamicGraphGroup.getChildren().addAll(secondLevelGroup, firstToSecondLevelGroup, firstLevelGroup);
    }

}
