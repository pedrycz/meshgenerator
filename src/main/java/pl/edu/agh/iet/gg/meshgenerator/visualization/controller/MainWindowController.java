package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.ComponentFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.EdgeFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.VertexFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl.ConstantEdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl.ConstantNodeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl.GridPositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard.CameraGroupKeyboardEventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.CameraGroupMouseEventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.VertexMouseEventManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bartłomiej Grochal
 */
public class MainWindowController {

    @FXML private RotatableGroup environmentGroup;
    @FXML private Group graphGroup;

    private Map<Class, ComponentFactory> componentFactories;
    private Map<Class, EventManager> eventManagers;
    private Graph graph;


    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        environmentGroup.getRotationStrategy().setInitialValues();
        setComponentFactories();
    }


    public void initializeGraph() {
        this.graph = new Graph();

        Node root = ((VertexFactory) componentFactories.get(VertexFactory.class)).getVertex(graph.getRoot());
        graphGroup.getChildren().add(root);
    }

    public RotatableGroup getEnvironmentGroup() {
        return environmentGroup;
    }

    public Group getGraphGroup() {
        return graphGroup;
    }

    public Graph getGraph() {
        return graph;
    }

    public Map<Class, EventManager> getEventManagers() {
        return eventManagers;
    }

    public void setEventManagers() {
        eventManagers = new HashMap<>();

        eventManagers.put(CameraGroupMouseEventManager.class, new CameraGroupMouseEventManager());
        eventManagers.put(CameraGroupKeyboardEventManager.class, new CameraGroupKeyboardEventManager());
        eventManagers.values().forEach(eventManager -> eventManager.setHandlers(environmentGroup));

        eventManagers.put(VertexMouseEventManager.class, new VertexMouseEventManager());
    }

    public Map<Class, ComponentFactory> getComponentFactories() {
        return componentFactories;
    }


    private void setComponentFactories() {
        componentFactories = new HashMap<>();

        NodePositioningStrategy nodePositioningStrategy = new GridPositioningStrategy();
        NodeRadiusStrategy nodeRadiusStrategy = new ConstantNodeRadiusStrategy();
        EdgeRadiusStrategy edgeRadiusStrategy = new ConstantEdgeRadiusStrategy();

        componentFactories.put(VertexFactory.class, new VertexFactory(nodePositioningStrategy, nodeRadiusStrategy));
        componentFactories.put(EdgeFactory.class, new EdgeFactory(edgeRadiusStrategy, nodePositioningStrategy));
    }

}
