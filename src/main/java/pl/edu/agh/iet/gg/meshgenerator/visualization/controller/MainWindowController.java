package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.ComponentFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.EdgeFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.VertexFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl.DecreasingEdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.impl.DecreasingNodeRadiusStrategy;
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

    @FXML private SubScene graphScene;
    @FXML private GraphController graphSceneController;

    private Map<Class, ComponentFactory> componentFactories;
    private Map<Class, EventManager> eventManagers;


    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        graphScene.setCamera(MainWindowUtil.getGraphSceneCamera());
        setComponentFactories();
    }


    public GraphController getGraphController() {
        return graphSceneController;
    }

    public Map<Class, ComponentFactory> getComponentFactories() {
        return componentFactories;
    }

    public Map<Class, EventManager> getEventManagers() {
        return eventManagers;
    }

    public void setEventManagers() {
        eventManagers = new HashMap<>();

        eventManagers.put(CameraGroupMouseEventManager.class, new CameraGroupMouseEventManager());
        eventManagers.put(CameraGroupKeyboardEventManager.class, new CameraGroupKeyboardEventManager());
        eventManagers.put(VertexMouseEventManager.class, new VertexMouseEventManager());
    }


    private void setComponentFactories() {
        componentFactories = new HashMap<>();

        NodePositioningStrategy nodePositioningStrategy = new GridPositioningStrategy();
        NodeRadiusStrategy nodeRadiusStrategy = new DecreasingNodeRadiusStrategy();
        EdgeRadiusStrategy edgeRadiusStrategy = new DecreasingEdgeRadiusStrategy();

        componentFactories.put(VertexFactory.class, new VertexFactory(nodePositioningStrategy, nodeRadiusStrategy));
        componentFactories.put(EdgeFactory.class, new EdgeFactory(edgeRadiusStrategy, nodePositioningStrategy));
    }

}
