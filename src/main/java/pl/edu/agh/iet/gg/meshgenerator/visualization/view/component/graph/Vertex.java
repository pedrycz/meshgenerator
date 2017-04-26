package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.scene.shape.Sphere;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.VertexMouseEventManager;

/**
 * @author Bartłomiej Grochal
 */
public class Vertex extends Sphere {

    private final Node node;


    public Vertex(double radius, double[] translations, Node node) {
        super(radius);

        this.node = node;
        initialize(translations);
    }


    private void initialize(double[] translations) {
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);

        if (node instanceof I) {
            setMaterial(Constants.I_VERTEX_MATERIAL);
        } else {
            setMaterial(((E) node).isExpanded() ?
                    Constants.E_VERTEX_EXPANDED_MATERIAL : Constants.E_VERTEX_UNEXPANDED_MATERIAL);

            ((E) node).isExpandedProperty().addListener((observable, oldValue, newValue) ->
                    setMaterial(Constants.E_VERTEX_EXPANDED_MATERIAL)   // When value changed, node can be expanded only.
            );
        }

        setEventHandlers();
    }

    private void setEventHandlers() {
        MainWindowUtil.getMainWindowController().getEventManagers()
                .get(VertexMouseEventManager.class)
                .setHandlers(this);
    }

    public Node getNode() {
        return node;
    }

}
