package pl.edu.agh.iet.gg.meshgenerator.visualization.util.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.iet.gg.meshgenerator.visualization.GraphVisualiser;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.MainWindowController;

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

        Scene mainWindowScene = new Scene(root, 800, 600);
        mainWindowScene.setCamera(getMainWindowCamera());
        stage.setScene(mainWindowScene);
        stage.show();

        // We need to set event handlers outside the initialize() method of the controller, because:
        // 1) some handlers are set on main scene, which value is null during initialization of the controller;
        // 2) method for initializing graph calls the controller's methods.
        mainWindowController = mainLoader.getController();
        mainWindowController.setEventManagers();
        mainWindowController.initializeGraph();
    }

    /**
     * Note: this method should never be invoked before the {@link #setMainWindowAttributes(Stage, String)} method.
     * Otherwise its result will be {@code null}, which may easily produce unexpected {@code NullPointerException}.
     */
    public static MainWindowController getMainWindowController() {
        return mainWindowController;
    }


    private static Camera getMainWindowCamera() {
        Camera mainWindowCamera = new PerspectiveCamera(true);
        mainWindowCamera.setNearClip(0.1);
        mainWindowCamera.setFarClip(10000.0);
        mainWindowCamera.setTranslateX(0.0);
        mainWindowCamera.setTranslateY(0.0);
        mainWindowCamera.setTranslateZ(-1500.0);

        return mainWindowCamera;
    }

}
