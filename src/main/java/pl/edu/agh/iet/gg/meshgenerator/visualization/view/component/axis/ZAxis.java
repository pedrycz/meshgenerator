package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Bartłomiej Grochal
 */
public class ZAxis extends Axis {

    public ZAxis() {
        super(new Point3D(1.0, 0.0, 0.0), Color.valueOf(Config.getString("component.axis.ZAxisColor")), "OZ");
    }

}
