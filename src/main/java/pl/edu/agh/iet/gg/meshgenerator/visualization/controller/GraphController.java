package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.VertexFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard.CameraGroupKeyboardEventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard.ProductionKeyboardEventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.CameraGroupMouseEventManager;

import java.util.Map;

/**
 * @author Bartłomiej Grochal
 */
public class GraphController {

    @FXML private RotatableGroup environmentGroup;
    @FXML private Group graphGroup;

    private Graph graph;
    private Vertex activeVertex;

    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        environmentGroup.getRotationStrategy().setInitialValues();
        MainWindowUtil.gainFocus(environmentGroup);
    }


    public void initializeGraph(VertexFactory vertexFactory) {
        this.graph = new Graph();

        Node root = vertexFactory.getVertex(graph.getRoot());
        graphGroup.getChildren().add(root);

        this.activeVertex = vertexFactory.getVertex(graph.getRoot());
    }

    public void setCameraHandlers(Map<Class, EventManager> eventManagers) {
        eventManagers.get(CameraGroupMouseEventManager.class).setHandlers(environmentGroup);
        eventManagers.get(CameraGroupKeyboardEventManager.class).setHandlers(environmentGroup);
        eventManagers.get(ProductionKeyboardEventManager.class).setHandlers(environmentGroup);
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

    public pl.edu.agh.iet.gg.meshgenerator.model.Node getActiveNode() {
        return activeVertex.getNode();
    }

    public void setNewActiveVertex(Vertex newActiveVertex) {
        activeVertex.setAsInactive();
        newActiveVertex.setAsActive();
        activeVertex = newActiveVertex;
    }
}
