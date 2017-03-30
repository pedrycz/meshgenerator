package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event;

import javafx.scene.Node;

/**
 * @author Bartłomiej Grochal
 */
public interface EventManager {

    // TODO: Move all to properties.
    double ROTATION_SPEED = 1.0;

    double MAX_ZOOM_SCALE = 5.0;
    double MIN_ZOOM_SCALE = 0.5;
    double ZOOM_SCALE = 1.1;


    void setEvents(Node target);

}
