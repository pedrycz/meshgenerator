package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private BooleanProperty isActiveProperty;

    public Vertex(double radius, double[] translations, Node node) {
        super(radius);

        this.node = node;
        this.isActiveProperty = new SimpleBooleanProperty(false);
        initialize(translations);
    }


    private void initialize(double[] translations) {
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);

        if (node instanceof I) {
            setMaterial(Constants.I_VERTEX_MATERIAL);
            isActiveProperty.addListener((observable, oldValue, newValue) -> {
                if (newValue)
                    // vertex set as active (clicked)
                    setMaterial(Constants.I_ACTIVE_VERTEX_MATERIAL);
                else
                    // vertex no longer active
                    setMaterial(Constants.I_VERTEX_MATERIAL);
            });
        } else {
            setMaterial(((E) node).isExpanded() ?
                    Constants.E_VERTEX_EXPANDED_MATERIAL : Constants.E_VERTEX_UNEXPANDED_MATERIAL);

            isActiveProperty.addListener((observable, oldValue, newValue) -> {
                if (newValue)
                    // vertex set as active (clicked)
                    setMaterial(Constants.E_ACTIVE_VERTEX_MATERIAL);
                else
                    // vertex no longer active
                    setMaterial(((E) node).isExpanded() ?
                        Constants.E_VERTEX_EXPANDED_MATERIAL : Constants.E_VERTEX_UNEXPANDED_MATERIAL);
            });
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

    public void setAsActive() {
        this.isActiveProperty.set(true);
    }

    public void setAsInactive() {
        this.isActiveProperty.set(false);
    }
}
