package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;

/**
 * @author Bartłomiej Grochal
 */
public class CameraGroupMouseEventManager implements EventManager {

    private double lastPressedPositionX;
    private double lastPressedPositionY;


    @Override
    public void setEvents(Node target) {
        target.getScene().setOnMousePressed(getMousePressedEventHandler(target));
        target.getScene().setOnMouseDragged(getMouseDraggedEventHandler(target));
        target.getScene().setOnScroll(getScrollEventHandler(target));
    }


    private EventHandler<MouseEvent> getMousePressedEventHandler(Node target) {
        return event -> {
            lastPressedPositionX = event.getSceneX();
            lastPressedPositionY = event.getSceneY();
        };
    }

    private EventHandler<MouseEvent> getMouseDraggedEventHandler(Node target) {
        return event -> {
            double currentPressedPositionX = event.getSceneX();
            double currentPressedPositionY = event.getSceneY();
            double mousePositionDeltaX = currentPressedPositionX - lastPressedPositionX;
            double mousePositionDeltaY = currentPressedPositionY - lastPressedPositionY;

            RotatableGroup targetCasted = (RotatableGroup) target;

            if (event.isPrimaryButtonDown()) {
                targetCasted.getRotationStrategy().handleMouseRotation(mousePositionDeltaX, mousePositionDeltaY);
            }

            lastPressedPositionX = currentPressedPositionX;
            lastPressedPositionY = currentPressedPositionY;
        };
    }

    private EventHandler<ScrollEvent> getScrollEventHandler(Node target) {
        return event -> {
            RotatableGroup targetCasted = (RotatableGroup) target;

            double targetScale = targetCasted.getScaling();
            targetScale = event.getDeltaY() < 0 ? targetScale / ZOOM_SCALE : targetScale * ZOOM_SCALE;
            targetScale = Double.compare(targetScale, MIN_ZOOM_SCALE) < 0 ? MIN_ZOOM_SCALE
                    : Double.compare(targetScale, MAX_ZOOM_SCALE) > 0 ? MAX_ZOOM_SCALE : targetScale;

            targetCasted.setScaling(targetScale);
        };
    }

}
