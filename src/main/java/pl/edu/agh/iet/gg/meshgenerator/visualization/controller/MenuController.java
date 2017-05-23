package pl.edu.agh.iet.gg.meshgenerator.visualization.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.ComponentFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.EdgeFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.VertexFactory;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Edge;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.menu.CenteredTextField;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Exchanger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil.getMainWindowController;

/**
 * @author Bartłomiej Grochal
 */
public class MenuController {

    @FXML
    private CenteredTextField graphFirstLevel;
    @FXML
    private CenteredTextField graphLastLevel;
    @FXML
    private Button repaintButton;


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

    @FXML
    public void save(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Graph");
        Graph graph = MainWindowUtil.getMainWindowController().getGraphController().getGraph();
        File file = fileChooser.showSaveDialog(MainWindowUtil.getApplicationStage());
        if (file != null) {
            try (
                    OutputStream outputStream = new FileOutputStream(file.getPath());
                    OutputStream buffer = new BufferedOutputStream(outputStream);
                    ObjectOutput output = new ObjectOutputStream(buffer);
            ) {
                output.writeObject(graph);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void open(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(MainWindowUtil.getApplicationStage());
        MainWindowController mainWindowController = MainWindowUtil.getMainWindowController();
        mainWindowController.getGraphController().getGraph();//
        try (
                InputStream outputStream = new FileInputStream(file.getPath());
                InputStream buffer = new BufferedInputStream(outputStream);
                ObjectInput output = new ObjectInputStream(buffer)
        ) {
            Graph graph = (Graph) output.readObject();
            this.graphFirstLevel.setValid(true);
            this.graphFirstLevel.setText("0");
            this.graphLastLevel.setValid(true);
            this.graphLastLevel.setText(String.valueOf(graph.getLevelsNumber()));
            Map<Class, ComponentFactory> componentFactories = getMainWindowController().getComponentFactories();
            getMainWindowController().getGraphController().getGraphGroup().getChildren().clear();

            graph.getVertex().add(graph.getRoot());
            getMainWindowController().getGraphController().getGraphGroup().getChildren().addAll(
                    graph.getVertex().stream()
                            .map(((VertexFactory) componentFactories.get(VertexFactory.class))::getVertex)
                            .collect(toList()));

            getMainWindowController().getGraphController().getGraphGroup().getChildren().addAll(
                    graph.getEdges().stream()
                            .map(((EdgeFactory) componentFactories.get(EdgeFactory.class))::getEdge)
                            .collect(toList()));


        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }
}
