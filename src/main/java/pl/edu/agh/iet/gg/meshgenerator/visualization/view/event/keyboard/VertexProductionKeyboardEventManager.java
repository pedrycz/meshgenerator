package pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.ProductionResults;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager;

import static pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.MainWindowUtil.getMainWindowController;
import static pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.NodeUtil.visualizeProductionResults;

/**
 * @author Piotr Oramus
 */
public class VertexProductionKeyboardEventManager implements EventManager {

    @Override
    public void setHandlers(Node target) {
        target.getScene().addEventHandler(KeyEvent.KEY_PRESSED, getKeyPressedEventHandler(target));
    }


    private EventHandler<KeyEvent> getKeyPressedEventHandler(Node target) {
        return event -> {

            pl.edu.agh.iet.gg.meshgenerator.model.Node activeNode = getMainWindowController().getGraphController().getActiveNode();

            switch (event.getCode()) {
                case A:
                    if (activeNode instanceof I) {

                        /* Trying to apply production P2a. */
                        I node = (I) activeNode;
                        if (!node.canApplyP2a(node.getNW(), node.getNE())) {
                            return;
                        }

                        ProductionResults results = node.applyP2a(node.getNW(), node.getNE());
                        visualizeProductionResults(results);
                    }
                    break;
                case B:
                    if (activeNode instanceof I) {

                        /* Trying to apply production P2b. */
                        I node = (I) activeNode;
                        if (!node.canApplyP2b(node.getNW(), node.getSW())) {
                            return;
                        }

                        ProductionResults results = node.applyP2b(node.getNW(), node.getSW());
                        visualizeProductionResults(results);
                    }
                    break;
                case C:
                    if (activeNode instanceof I) {

                        /* Trying to apply production P2c. */
                        I node = (I) activeNode;
                        if (!node.canApplyP2c(node.getSW(), node.getSE())) {
                            return;
                        }

                        ProductionResults results = node.applyP2c(node.getSW(), node.getSE());
                        visualizeProductionResults(results);
                    }
                    break;
                case D:
                    if (activeNode instanceof I) {

                        /* Trying to apply production P2d. */
                        I node = (I) activeNode;
                        if (!node.canApplyP2d(node.getNE(), node.getSE())) {
                            return;
                        }

                        ProductionResults results = node.applyP2d(node.getNE(), node.getSE());
                        visualizeProductionResults(results);
                    }
                    break;
                case DIGIT1:
                    System.out.println("Trying to apply P1");
                    if (activeNode instanceof E) {

                        /* Applying production P1 if possible */
                        E node = (E) activeNode;

                        if (!node.canApplyP1()) {
                            return;
                        }

                        ProductionResults results = node.applyP1();
                        visualizeProductionResults(results);
                    }
                    break;
                case DIGIT3:
                    System.out.println("Trying to apply P3");
                    if (activeNode instanceof I) {

                        /* Applying production P3 if possible */
                        I node = (I) activeNode;

                        // TODO: this assumes that the P3 CAN be applied
                        E e1 = node.getSW().getBelow().get().getNE();
                        E e2 = node.getNW().getBelow().get().getSE();
                        E e3 = node.getNE().getBelow().get().getSW();
                        E e4 = node.getSE().getBelow().get().getNW();

                        ProductionResults results = E.applyP3(e1, e2, e3, e4);
                        visualizeProductionResults(results);
                    }
                    break;
                default:
                    break;
            }
        };
    }

}