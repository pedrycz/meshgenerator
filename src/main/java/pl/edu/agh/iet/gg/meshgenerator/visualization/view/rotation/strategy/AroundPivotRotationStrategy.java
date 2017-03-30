package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.strategy;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.RotateDirection;

import java.util.Arrays;
import java.util.List;

import static pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager.ROTATION_SPEED;

/**
 * @author Bartłomiej Grochal
 */
@SuppressWarnings("unused")
public class AroundPivotRotationStrategy implements RotationStrategy {

    // TODO: Move to properties. Unify with the same constant in the AroundPivotRotationStrategy class.
    private static final double KEYBOARD_DELTA = 1.0;


    /**
     * Defines rotation of this group around specified {@code pivot} point, along X axis.
     */
    private final Rotate rotationX;

    /**
     * Defines rotation of this group around specified {@code pivot} point, along Y axis.
     */
    private final Rotate rotationY;

    /**
     * Defines rotation of this group around specified {@code pivot} point, along Z axis.
     */
    private final Rotate rotationZ;


    public AroundPivotRotationStrategy() {
        rotationX = new Rotate(0, Rotate.X_AXIS);
        rotationY = new Rotate(0, Rotate.Y_AXIS);
        rotationZ = new Rotate(0, Rotate.Z_AXIS);
    }


    @Override
    public void setInitialValues() {
        setRotationX(-45.0);
        setRotationY(-45.0);
        setRotationZ(-35.0);
    }

    @Override
    public void handleMouseRotation(double mousePositionDeltaX, double mousePositionDeltaY) {
        setRotationX(getRotationX() - mousePositionDeltaY * ROTATION_SPEED);
        setRotationY(getRotationY() - mousePositionDeltaX * ROTATION_SPEED);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void handleKeyboardRotation(RotateDirection direction) {
        switch (direction) {
            case UP:
            case DOWN:
                setKeyboardRotation(rotationX, direction);
                break;
            case RIGHT:
            case LEFT:
                setKeyboardRotation(rotationY, direction);
                break;
            default:
                break;
        }

    }

    @Override
    public List<Transform> getRotations() {
        return Arrays.asList(rotationZ, rotationY, rotationX);
    }


    public void setRotation(double rotationX, double rotationY, double rotationZ) {
        this.rotationX.setAngle(rotationX);
        this.rotationY.setAngle(rotationY);
        this.rotationZ.setAngle(rotationZ);
    }

    public double getRotationX() {
        return rotationX.getAngle();
    }

    public void setRotationX(double rotationX) {
        this.rotationX.setAngle(rotationX);
    }

    public Rotate getRotationXTransformation() {
        return rotationX;
    }

    public double getRotationY() {
        return rotationY.getAngle();
    }

    public void setRotationY(double rotationY) {
        this.rotationY.setAngle(rotationY);
    }

    public Rotate getRotationYTransformation() {
        return rotationY;
    }

    public double getRotationZ() {
        return rotationZ.getAngle();
    }

    public void setRotationZ(double rotationZ) {
        this.rotationZ.setAngle(rotationZ);
    }

    public Rotate getRotationZTransformation() {
        return rotationZ;
    }


    private void setKeyboardRotation(Rotate rotation, RotateDirection direction) {
        rotation.setAngle(rotation.getAngle() + direction.getDirectionSign() * KEYBOARD_DELTA * ROTATION_SPEED);
    }

}
