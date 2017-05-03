package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.RotateDirection;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;

/**
 * @author Bartłomiej Grochal
 */
public class CameraGroupKeyboardEventManager implements EventManager {

    @Override
    public void setHandlers(Node target) {
        target.getScene().addEventHandler(KeyEvent.KEY_PRESSED, getKeyPressedEventHandler(target));
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
