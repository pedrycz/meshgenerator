package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.mouse;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.ProductionResults;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.graph.Vertex;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;

import static pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil.getMainWindowController;
import static pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.NodeUtil.visualizeProductionResults;

/**
 * @author Bartłomiej Grochal
 */
public class VertexMouseEventManager implements EventManager {

    @Override
    public void setHandlers(Node target) {
        target.addEventHandler(MouseEvent.MOUSE_PRESSED, getMousePressedEventHandler(target));
    }


    private EventHandler<MouseEvent> getMousePressedEventHandler(Node target) {
        return event -> {
            if (event.isControlDown()) {
                /* Zooming. */

                // TODO: Should we mark selected scaling pivot in some way?
                getMainWindowController().getGraphController().getEnvironmentGroup()
                        .setScalingPivot(target.getTranslateX(), target.getTranslateY(), target.getTranslateZ());
                return;
            }

            Vertex targetCasted = (Vertex) target;

            if (targetCasted.getNode() instanceof E) {
                /* Applying production on E node. */

                E node = (E) targetCasted.getNode();
                if (!node.canApplyP1()) {
                    return;
                }

                ProductionResults results = node.applyP1();
                visualizeProductionResults(results);
            } else if (targetCasted.getNode() instanceof I) {
                /* Applying production on I node. */

                I node = (I) targetCasted.getNode();
                if (!node.canApplyP2a(node.getNW(), node.getNE())) {
                    return;
                }

                ProductionResults results = node.applyP2a(node.getNW(), node.getNE());
                visualizeProductionResults(results);
            }
        };
    }

}
