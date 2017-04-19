package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import static pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis.Axis.AXIS_HEAD_HEIGHT;
import static pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis.Axis.AXIS_HEAD_SIDE;

/**
 * @author Bartłomiej Grochal
 */
public class AxisHead extends MeshView {

    private TriangleMesh axisHeadMesh;


    public AxisHead() {
        this(new PhongMaterial(), new double[]{0.0, 0.0, 0.0});
    }

    public AxisHead(Material material) {
        this(material, new double[]{0.0, 0.0, 0.0});
    }

    public AxisHead(double[] translations) {
        this(new PhongMaterial(), translations);
    }

    public AxisHead(Material material, double[] translations) {
        super();
        initializeMesh();
        initializeView(material, translations);
    }


    private void initializeMesh() {
        axisHeadMesh = new TriangleMesh();
        float height = AXIS_HEAD_HEIGHT;
        float side = AXIS_HEAD_SIDE;

        axisHeadMesh.getTexCoords().addAll(
                0.0f, 0.0f
        );
        axisHeadMesh.getPoints().addAll(
                0.0f,       0.0f,       0.0f,
                0.0f,       height,     -side / 2,
                -side / 2,  height,     0.0f,
                side / 2,   height,     0.0f,
                0.0f,       height,     side / 2
        );
        axisHeadMesh.getFaces().addAll(
                0, 0, 2, 0, 1, 0,
                0, 0, 1, 0, 3, 0,
                0, 0, 3, 0, 4, 0,
                0, 0, 4, 0, 2, 0,
                4, 0, 1, 0, 2, 0,
                4, 0, 3, 0, 1, 0
        );
    }

    private void initializeView(Material material, double[] translations) {
        setMesh(axisHeadMesh);

        setDrawMode(DrawMode.FILL);
        setMaterial(material);

        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);
    }

}
