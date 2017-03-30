package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.util.RotateDirection;

/**
 * @author Bartłomiej Grochal
 */
public class CameraGroupKeyboardEventManager implements EventManager {

    @Override
    public void setEvents(Node target) {
        target.getScene().setOnKeyPressed(getKeyPressedEventHandler(target));
    }


    private EventHandler<KeyEvent> getKeyPressedEventHandler(Node target) {
        return event -> {
            RotatableGroup targetCasted = (RotatableGroup) target;

            switch (event.getCode()) {
                case RIGHT:
                    targetCasted.getRotationStrategy().handleKeyboardRotation(RotateDirection.RIGHT); break;
                case LEFT:
                    targetCasted.getRotationStrategy().handleKeyboardRotation(RotateDirection.LEFT); break;
                case UP:
                    targetCasted.getRotationStrategy().handleKeyboardRotation(RotateDirection.UP); break;
                case DOWN:
                    targetCasted.getRotationStrategy().handleKeyboardRotation(RotateDirection.DOWN); break;
                default:
                    break;
            }
        };
    }

}
