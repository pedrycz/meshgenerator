package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

/**
 * @author Bartłomiej Grochal
 */
public class XAxis extends Axis {

    public XAxis() {
        super(new Point3D(0.0, 0.0, 1.0), Color.RED, "OX");
    }

}
