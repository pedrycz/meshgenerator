package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Bartłomiej Grochal
 */
public class YAxis extends Axis {

    public YAxis() {
        super(new Point3D(0.0, 1.0, 0.0), Color.valueOf(Config.getString("component.axis.YAxisColor")), "OY");
    }

}
