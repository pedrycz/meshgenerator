package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author Bartłomiej Grochal
 */
public class XYRotate extends PlaneRotate {

    /**
     * Matrix for rotation around Z axis:
     * [cos(phi)   -sin(phi)    0]
     * [sin(phi)    cos(phi)    0]
     * [    0          0        1]
     */
    @Override
    void onAngleChange(double angle) {
        setMxx(cos(angle));
        setMxy(-sin(angle));
        setMxz(0.0);

        setMyx(sin(angle));
        setMyy(cos(angle));
        setMyz(0.0);

        setMzx(0.0);
        setMzy(0.0);
        setMzz(1.0);

        setTx(0.0);
        setTy(0.0);
        setTz(0.0);
    }

}
