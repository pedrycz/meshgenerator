package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.scene.shape.Sphere;
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
        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);

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
