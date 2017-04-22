package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event;

import javafx.scene.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Bartłomiej Grochal
 */
public interface EventManager {

    double ROTATION_SPEED = Config.getDouble("rotation.Speed");

    double MIN_ZOOM_SCALE = Config.getDouble("zoom.MinScale");
    double MAX_ZOOM_SCALE = Config.getDouble("zoom.MaxScale");
    double ZOOM_SCALE = Config.getDouble("zoom.ScaleFactor");


    void setHandlers(Node target);

}
