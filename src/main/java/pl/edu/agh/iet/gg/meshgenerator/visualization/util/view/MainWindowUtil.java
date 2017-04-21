package pl.edu.agh.iet.gg.meshgenerator.visualization.util.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import pl.edu.agh.iet.gg.meshgenerator.visualization.GraphVisualiser;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.MainWindowController;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.factory.VertexFactory;

import java.io.IOException;

/**
 * @author Bartłomiej Grochal
 */
public final class MainWindowUtil {

    private static MainWindowController mainWindowController;


    private MainWindowUtil() {
    }


    public static void setMainWindowAttributes(Stage stage, String mainWindowViewResourcePath) throws IOException {
        stage.setTitle("Graph Visualiser");

        FXMLLoader mainLoader = new FXMLLoader(GraphVisualiser.class.getResource(mainWindowViewResourcePath));
        Parent root = mainLoader.load();

        mainWindowController = mainLoader.getController();
        Scene mainWindowScene = new Scene(root, 800, 600);
        stage.setScene(mainWindowScene);
        stage.show();
    }

    public static void initializeMainWindow() {
        // We need to call these methods outside the initialize() method of the controller, because:
        // 1) some handlers are set on main scene, which value is null during initialization of the controller;
        // 2) method for initializing graph calls the controller's methods.
        mainWindowController.setEventManagers();
        mainWindowController.getGraphController()
                .initializeGraph((VertexFactory) mainWindowController.getComponentFactories().get(VertexFactory.class));
        mainWindowController.getGraphController().setCameraHandlers(mainWindowController.getEventManagers());
    }

    /**
     * Note: this method should never be invoked before the {@link #setMainWindowAttributes(Stage, String)} method.
     * Otherwise its result will be {@code null}, which may easily produce unexpected {@code NullPointerException}.
     */
    public static MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public static Camera getGraphSceneCamera() {
        Camera graphSceneCamera = new PerspectiveCamera(true);
        graphSceneCamera.setNearClip(0.1);
        graphSceneCamera.setFarClip(10000.0);
        graphSceneCamera.setTranslateX(0.0);
        graphSceneCamera.setTranslateY(0.0);
        graphSceneCamera.setTranslateZ(-1500.0);

        return graphSceneCamera;
    }

    public static void gainFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
            }
        });
    }

}
