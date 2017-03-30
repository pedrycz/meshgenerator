package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.strategy;

import pl.edu.agh.iet.gg.meshgenerator.model.Node;

/**
 * @author Wojciech Pachuta.
 */
public interface NodePositioningStrategy {

    double[] getPosition(Node node);

}
