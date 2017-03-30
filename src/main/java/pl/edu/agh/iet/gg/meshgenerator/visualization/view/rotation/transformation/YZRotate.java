package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author Bartłomiej Grochal
 */
public class YZRotate extends PlaneRotate {

    /**
     * Matrix for rotation around X axis:
     * [1       0          0    ]
     * [0    cos(phi)  -sin(phi)]
     * [0    sin(phi)   cos(phi)]
     */
    @Override
    void onAngleChange(double angle) {
        setMxx(1.0);
        setMxy(0.0);
        setMxz(0.0);

        setMyx(0.0);
        setMyy(cos(angle));
        setMyz(-sin(angle));

        setMzx(0.0);
        setMzy(sin(angle));
        setMzz(cos(angle));

        setTx(0.0);
        setTy(0.0);
        setTz(0.0);
    }

}
