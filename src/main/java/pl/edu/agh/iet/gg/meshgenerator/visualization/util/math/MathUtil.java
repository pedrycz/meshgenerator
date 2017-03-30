package pl.edu.agh.iet.gg.meshgenerator.visualization.util.math;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;

/**
 * @author Bartłomiej Grochal
 */
public class MathUtil {

    private MathUtil() {
    }


    /**
     * Calculates distance between given {@link Point2D 2D point} and given {@link Node target node}, which position is
     * specified by proper translation properties.
     */
    public static double distanceXY(Point2D point, Node target) {
        return Math.sqrt(Math.pow(point.getX() - target.getTranslateX(), 2.0)
                + Math.pow(point.getY() - target.getTranslateY(), 2.0));
    }

    /**
     * Calculates distance between given {@link Point3D 3D point} and given {@link Node target node}, which position is
     * specified by proper translation properties.
     */
    public static double distanceXYZ(Point3D point, Node target) {
        return Math.sqrt(Math.pow(point.getX() - target.getTranslateX(), 2.0)
                + Math.pow(point.getY() - target.getTranslateY(), 2.0)
                + Math.pow(point.getZ() - target.getTranslateZ(), 2.0));
    }

}
