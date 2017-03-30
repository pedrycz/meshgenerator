package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;

/**
 * @author Bartłomiej Grochal
 */
public class VertexMouseEventManager implements EventManager {

    private RotatableGroup environmentGroup;


    public VertexMouseEventManager(RotatableGroup environmentGroup) {
        this.environmentGroup = environmentGroup;
    }


    @Override
    public void setEvents(Node target) {
        target.addEventHandler(MouseEvent.MOUSE_PRESSED, getMousePressedEventHandler(target));
    }


    private EventHandler<MouseEvent> getMousePressedEventHandler(Node target) {
        return event -> {
            if (!event.isControlDown()) {
                return;
            }

            // TODO: Should we mark selected scaling pivot in some way?
            environmentGroup.setScalingPivot(target.getTranslateX(), target.getTranslateY(), target.getTranslateZ());
        };
    }

}
