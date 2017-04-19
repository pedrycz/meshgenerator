package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;

/**
 * @author Bartłomiej Grochal
 */
public interface Constants {

    Material E_VERTEX_UNEXPANDED_MATERIAL = new PhongMaterial(Color.DARKRED);
    Material E_VERTEX_EXPANDED_MATERIAL = new PhongMaterial(Color.CRIMSON);
    Material I_VERTEX_MATERIAL = new PhongMaterial(Color.DODGERBLUE);
    Material EDGE_MATERIAL = new PhongMaterial(Color.SLATEGRAY);

}
