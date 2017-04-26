package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu.CenteredTextField;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Bartłomiej Grochal
 */
public class MenuController {

    @FXML private CenteredTextField graphFirstLevel;
    @FXML private CenteredTextField graphLastLevel;
    @FXML private Button repaintButton;


    @FXML
    private void initialize() {
        BooleanBinding isRepaintUnavailable = Bindings.createBooleanBinding(
                () -> !(graphFirstLevel.isValid() && graphLastLevel.isValid() &&
                        getNumberFieldValue(graphFirstLevel) <= getNumberFieldValue(graphLastLevel)),
                graphFirstLevel.validProperty(), graphFirstLevel.textProperty(),
                graphLastLevel.validProperty(), graphLastLevel.textProperty());
        repaintButton.disableProperty().bind(isRepaintUnavailable);
    }

    @FXML
    private void validateGraphLevel(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        CenteredTextField eventSource = (CenteredTextField) ((ReadOnlyBooleanProperty) observable).getBean();

        if (newValue) {
            eventSource.unmarkInvalidInput();
        } else {
            if (eventSource.getText().length() == 0) {
                eventSource.setValid(false);
                return;
            }

            Graph displayedGraph = MainWindowUtil.getMainWindowController().getGraphController().getGraph();
            int fieldValue = getNumberFieldValue(eventSource);

            if (fieldValue < 0 || fieldValue >= displayedGraph.getLevelsNumber()) {
                eventSource.setValid(false);
                eventSource.markInvalidInput();
            } else {
                eventSource.setValid(true);
            }
        }
    }

    @FXML
    private void repaintGraph(MouseEvent mouseEvent) {
        Predicate<Node> verticesPredicate = node -> (node instanceof Vertex);
        Predicate<Node> edgesPredicate = node -> (node instanceof Edge);

        Predicate<Node> verticesToShowPredicate = node ->
                ((Vertex) node).getNode().getLevel() >= getNumberFieldValue(graphFirstLevel) &&
                ((Vertex) node).getNode().getLevel() <= getNumberFieldValue(graphLastLevel);
        Predicate<Node> edgesToShowPredicate = node ->
                ((Edge) node).getEdge().getA().getLevel() >= getNumberFieldValue(graphFirstLevel) &&
                ((Edge) node).getEdge().getB().getLevel() <= getNumberFieldValue(graphLastLevel);

        List<Node> graphVisualization =
                MainWindowUtil.getMainWindowController().getGraphController().getGraphGroup().getChildren();

        // Hiding unwanted elements.
        graphVisualization.stream()
                .filter(verticesPredicate.and(verticesToShowPredicate.negate()))
                .forEach(node -> node.setVisible(false));
        graphVisualization.stream()
                .filter(edgesPredicate.and(edgesToShowPredicate.negate()))
                .forEach(node -> node.setVisible(false));

        // Showing requested elements.
        graphVisualization.stream()
                .filter(verticesPredicate.and(verticesToShowPredicate))
                .forEach(node -> node.setVisible(true));
        graphVisualization.stream()
                .filter(edgesPredicate.and(edgesToShowPredicate))
                .forEach(node -> node.setVisible(true));
    }


    private int getNumberFieldValue(CenteredTextField eventSource) {
        try {
            return Integer.parseInt(eventSource.getText());
        } catch (NumberFormatException exc) {
            return -1;
        }
    }

}
