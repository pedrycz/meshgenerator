package pl.edu.agh.iet.gg.meshgenerator.visualization.util.view;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.transform.NonInvertibleTransformException;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.RotatableGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Bartłomiej Grochal
 */
public class NodeUtil {

    private NodeUtil() {
    }


    public static Optional<Point3D> getRealPointCoordinates(Point3D point, RotatableGroup containingGroup) {
        try {
            return Optional.of(containingGroup.getLocalToSceneTransform().inverseTransform(point));
        } catch (NonInvertibleTransformException exc) {
            exc.printStackTrace();
        }

        return Optional.empty();
    }

    public static List<Node> getAllDescendantsFiltered(Parent rootNode, Predicate<Node> filterPredicate) {
        return getAllDescendants(rootNode).stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());
    }

    public static List<Node> getAllDescendants(Parent rootNode) {
        List<Node> descendants = new LinkedList<>();
        getAllDescendantsRecursively(rootNode, descendants);

        return descendants;
    }

    private static void getAllDescendantsRecursively(Parent rootNode, List<Node> descendants) {
        rootNode.getChildrenUnmodifiable().forEach(node -> {
            descendants.add(node);
            if (node instanceof Parent) {
                getAllDescendantsRecursively((Parent) node, descendants);
            }
        });
    }

}
