package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.strategy;

import javafx.scene.transform.Transform;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.util.RotateDirection;

import java.util.List;

/**
 * @author Bartłomiej Grochal
 */
public interface RotationStrategy {

    void setInitialValues();

    void handleMouseRotation(double mousePositionDeltaX, double mousePositionDeltaY);

    void handleKeyboardRotation(RotateDirection direction);


    List<Transform> getRotations();

}
