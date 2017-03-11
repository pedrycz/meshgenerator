package pl.edu.agh.iet.gg.meshgenerator.visualization.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.iet.gg.meshgenerator.visualization.GraphVisualiser;

import java.io.IOException;

/**
 * @author Bartłomiej Grochal
 */
public final class MainWindowUtil {

    private MainWindowUtil() {
    }


    public static void setMainWindowAttributes(Stage stage, String mainWindowViewResourcePath) throws IOException {
        stage.setTitle("Graph Visualiser");

        Parent root = FXMLLoader.load(GraphVisualiser.class.getResource(mainWindowViewResourcePath));
        Scene mainWindowScene = new Scene(root, 800, 600);
        mainWindowScene.setCamera(getMainWindowCamera());

        stage.setScene(mainWindowScene);
        stage.show();
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
