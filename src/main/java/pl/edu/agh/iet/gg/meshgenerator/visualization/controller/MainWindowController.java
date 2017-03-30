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
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.NodeUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.VerticalEdge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard.CameraGroupKeyboardEventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.CameraGroupMouseEventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.VertexMouseEventManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

/**
 * @author Bartłomiej Grochal
 */
public class MainWindowController {

    @FXML private RotatableGroup environmentGroup;
    @FXML private Group dynamicGraphGroup;

    private Map<Class, EventManager> eventManagers;
    private NodeVertexFactory nodeVertexFactory;
    private EdgeFactory edgeFactory;
    private Graph graph;

    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        eventManagers = new HashMap<>();
        environmentGroup.getRotationStrategy().setInitialValues();


        // TODO: try to refactor the code below.

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

        initializeGraph();
    }

    public void setEventHandlers() {
        eventManagers.put(CameraGroupMouseEventManager.class, new CameraGroupMouseEventManager());
        eventManagers.put(CameraGroupKeyboardEventManager.class, new CameraGroupKeyboardEventManager());
        eventManagers.values().forEach(eventManager -> eventManager.setEvents(environmentGroup));

        eventManagers.put(VertexMouseEventManager.class, new VertexMouseEventManager(environmentGroup));
        // TODO: Remove this line when Vertices will be created dynamically (not in FXML code).
        // TODO: When removing this line, invoke the Vertex#setEventHandlers() method from the constructor of Vertex class.
        NodeUtil.getAllDescendantsFiltered(environmentGroup, node -> node instanceof Vertex)
                .forEach(node -> ((Vertex) node).setEventHandlers());
    }

    public Map<Class, EventManager> getEventManagers() {
        return eventManagers;
    }


    private void initializeGraph() {
        Node root = nodeVertexFactory.getNodeVertex(graph.getRoot());
        dynamicGraphGroup.getChildren().add(root);
    }

}
