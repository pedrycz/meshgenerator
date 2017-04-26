package pl.edu.agh.iet.gg.meshgenerator.visualization;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;

/**
 * @author Bartłomiej Grochal
 */
public class GraphVisualizer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindowUtil.setMainWindowAttributes(primaryStage, "view/layout/MainWindowView.fxml");
        MainWindowUtil.initializeMainWindow();
    }

}
