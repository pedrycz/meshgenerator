package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.VerticalEdge;

/**
 * @author Bartłomiej Grochal
 */
public class MainWindowController {

    @FXML private RotatableGroup environmentGroup;
    @FXML private Group dynamicGraphGroup;


    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        environmentGroup.rx.setAngle(-45);
        environmentGroup.ry.setAngle(-45);
        environmentGroup.rz.setAngle(-35);

        createDynamicGraphExample();
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
