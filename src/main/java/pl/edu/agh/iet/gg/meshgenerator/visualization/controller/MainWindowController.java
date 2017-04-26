package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.ComponentFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.EdgeFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.VertexFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.EdgeRadiusStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodePositioningStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy.NodeRadiusStrategy;
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
    @FXML private GridPane menuPane;
    @FXML private GraphController graphSceneController;
    @FXML private MenuController menuPaneController;

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

    public MenuController getMenuController() {
        return menuPaneController;
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

        try {
            NodePositioningStrategy nodePositioningStrategy =
                    (NodePositioningStrategy) Class.forName(Config.getString("strategy.NodePositioning")).newInstance();
            NodeRadiusStrategy nodeRadiusStrategy =
                    (NodeRadiusStrategy) Class.forName(Config.getString("strategy.NodeRadius")).newInstance();
            EdgeRadiusStrategy edgeRadiusStrategy =
                    (EdgeRadiusStrategy) Class.forName(Config.getString("strategy.EdgeRadius")).newInstance();

            componentFactories.put(VertexFactory.class, new VertexFactory(nodePositioningStrategy, nodeRadiusStrategy));
            componentFactories.put(EdgeFactory.class, new EdgeFactory(edgeRadiusStrategy, nodePositioningStrategy));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
            exc.printStackTrace();
        }
    }

}
