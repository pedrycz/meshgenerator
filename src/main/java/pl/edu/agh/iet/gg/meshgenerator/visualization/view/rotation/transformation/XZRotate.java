package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author Bartłomiej Grochal
 */
public class XZRotate extends PlaneRotate {

    /**
     * Matrix for rotation around Y axis:
     * [ cos(phi)   0    sin(phi)]
     * [   0        1       0    ]
     * [-sin(phi)   0    cos(phi)]
     */
    @Override
    void onAngleChange(double angle) {
        setMxx(cos(angle));
        setMxy(0.0);
        setMxz(sin(angle));

        setMyx(0.0);
        setMyy(1.0);
        setMyz(0.0);

        setMzx(-sin(angle));
        setMzy(0.0);
        setMzz(cos(angle));

        setTx(0.0);
        setTy(0.0);
        setTz(0.0);
    }

}
