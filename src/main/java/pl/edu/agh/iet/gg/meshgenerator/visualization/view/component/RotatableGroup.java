package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component;

import javafx.scene.Group;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.strategy.AroundAxisRotationStrategy;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.strategy.RotationStrategy;

/**
 * This class has been created on the basis of the code taken from the <a
 * href="https://docs.oracle.com/javase/8/javafx/graphics-tutorial/sampleapp3d-code.htm#CJAGGIFG">Official JavaFX
 * tutorial</a>.
 *
 * @author Bartłomiej Grochal
 */
@SuppressWarnings("unused")
public class RotatableGroup extends Group {

    /**
     * Defines scalingFactor of this group with a (floating-point) factor.
     */
    private final Scale scaling;

    /**
     * Defines translation of this group with a 3D vector.
     */
    private final Translate translation;

    /**
     * Defines rotation type applied to this group.
     */
    private RotationStrategy rotationStrategy;


    public RotatableGroup() {
        super();

        scaling = new Scale();
        translation = new Translate();
        try {
            rotationStrategy = (RotationStrategy) Class.forName(Config.getString("strategy.Rotation")).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
            exc.printStackTrace();
            rotationStrategy = new AroundAxisRotationStrategy();    // default rotation strategy
        }

        getTransforms().addAll(rotationStrategy.getRotations());
        getTransforms().addAll(translation, scaling);
    }


    /*
     * SCALING.
     */
    public double getScaling() {
        return scaling.getX();
    }

    public void setScaling(double scalingFactor) {
        scaling.setX(scalingFactor);
        scaling.setY(scalingFactor);
        scaling.setZ(scalingFactor);
    }

    /**
     * Use this method instead of {@link javafx.scene.Node#setScaleX(double)} - it cannot be overridden, because it
     * has final modifier.
     */
    public void setScalingX(double scalingX) {
        scaling.setX(scalingX);
    }

    /**
     * Use this method instead of {@link javafx.scene.Node#setScaleY(double)} - it cannot be overridden, because it
     * has final modifier.
     */
    public void setScalingY(double scalingY) {
        scaling.setY(scalingY);
    }

    /**
     * Use this method instead of {@link javafx.scene.Node#setScaleZ(double)} - it cannot be overridden, because it
     * has final modifier.
     */
    public void setScalingZ(double scalingZ) {
        scaling.setZ(scalingZ);
    }

    /**
     * Sets coordinates of a point, which is a center of scalingFactor transformation.
     */
    public void setScalingPivot(double pivotX, double pivotY, double pivotZ) {
        scaling.setPivotX(pivotX);
        scaling.setPivotY(pivotY);
        scaling.setPivotZ(pivotZ);
    }


    /*
     * TRANSLATION.
     */
    public void setTranslation(double translationX, double translationY, double translationZ) {
        translation.setX(translationX);
        translation.setY(translationY);
        translation.setZ(translationZ);
    }

    /**
     * Use this method instead of {@link javafx.scene.Node#setTranslateX(double)} - it cannot be overridden, because it
     * has final modifier.
     */
    public void setTranslationX(double translationX) {
        translation.setX(translationX);
    }

    /**
     * Use this method instead of {@link javafx.scene.Node#setTranslateY(double)} - it cannot be overridden, because it
     * has final modifier.
     */
    public void setTranslationY(double translationY) {
        translation.setY(translationY);
    }

    /**
     * Use this method instead of {@link javafx.scene.Node#setTranslateZ(double)} - it cannot be overridden, because it
     * has final modifier.
     */
    public void setTranslationZ(double translationZ) {
        translation.setZ(translationZ);
    }


    /*
     * ROTATION.
     */
    public RotationStrategy getRotationStrategy() {
        return rotationStrategy;
    }

}
