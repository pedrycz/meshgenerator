package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph;

import javafx.scene.shape.Sphere;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse.VertexMouseEventManager;

/**
 * @author Bartłomiej Grochal
 */
public class Vertex extends Sphere {

    public Vertex() {
        this(15.0, new double[]{0, 0, 0});
    }

    public Vertex(double radius, double[] translations) {
        super(radius);

        setTranslateX(translations[0]);
        setTranslateY(translations[1]);
        setTranslateZ(translations[2]);

        // TODO: Uncomment when Vertices will be created dynamically (see: MainWindowController#setEventHandlers()).
        // setEventHandlers();
    }


    public void setEventHandlers() {
        MainWindowUtil.getMainWindowController().getEventManagers()
                .get(VertexMouseEventManager.class)
                .setEvents(this);
    }

}
