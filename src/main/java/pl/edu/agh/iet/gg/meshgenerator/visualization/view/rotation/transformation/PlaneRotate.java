package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.transform.Affine;

/**
 * @author Bartłomiej Grochal
 */
public abstract class PlaneRotate extends Affine {

    private DoubleProperty angle;


    abstract void onAngleChange(double angle);


    public final double getAngle() {
        return angle == null ? 0.0 : angle.get() / Math.PI * 180;
    }

    public final void setAngle(double angle) {
        angleProperty().set((angle / 180 * Math.PI) % (2 * Math.PI));
    }

    public final DoubleProperty angleProperty() {
        if (angle == null) {
            angle = new SimpleDoubleProperty();
            angle.addListener((observable, oldValue, newValue) -> onAngleChange(newValue.doubleValue()));
        }

        return angle;
    }

}
