package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;

/**
 * @author Bartłomiej Grochal
 */
public interface Constants {

    Material E_VERTEX_UNEXPANDED_MATERIAL =
            new PhongMaterial(Color.valueOf(Config.getString("component.vertex.EVertexUnexpandedColor")));
    Material E_VERTEX_EXPANDED_MATERIAL =
            new PhongMaterial(Color.valueOf(Config.getString("component.vertex.EVertexExpandedColor")));
    Material I_VERTEX_MATERIAL =
            new PhongMaterial(Color.valueOf(Config.getString("component.vertex.IVertexColor")));
    Material EDGE_MATERIAL =
            new PhongMaterial(Color.valueOf(Config.getString("component.edge.Color")));

}
