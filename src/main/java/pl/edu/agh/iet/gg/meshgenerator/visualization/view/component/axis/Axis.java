package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Bartłomiej Grochal
 */
public abstract class Axis extends Group {

    static final double AXIS_LINE_LENGTH = Config.getDouble("component.axis.LineLength");
    static final double AXIS_LINE_SIZE = Config.getDouble("component.axis.LineThickness");

    static final float AXIS_HEAD_HEIGHT = Config.getFloat("component.axis.HeadHeight");
    static final float AXIS_HEAD_SIDE = Config.getFloat("component.axis.HeadSide");


    private final AxisLine axisLine;
    private final AxisHead axisHead;
    private final AxisLabel axisLabel;


    public Axis(Point3D rotationVersor, Color color, String label) {
        PhongMaterial material = new PhongMaterial(color);
        double translationFactor = AXIS_LINE_LENGTH / 2 + AXIS_HEAD_HEIGHT;

        axisLine = new AxisLine(material);
        axisHead = new AxisHead(material, new double[]{0, -translationFactor, 0});
        axisLabel = new AxisLabel(label, new double[]{-20, -translationFactor - AXIS_HEAD_HEIGHT, 0});

        initialize(rotationVersor);
    }


    private void initialize(Point3D rotationVersor) {
        getChildren().addAll(axisLine, axisHead, axisLabel);
        setTranslateY(AXIS_HEAD_HEIGHT);
        setRotationAxis(rotationVersor);
        setRotate(90.0);
    }

}
