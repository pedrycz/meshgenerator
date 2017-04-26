package pl.edu.agh.iet.gg.meshgenerator.model;

import java.util.OptionalInt;

/**
 * @author Wojciech Pachuta.
 */
public class Graph {

    private final E root;


    public Graph() {
        this.root = new E(0,0,0);
    }


    public E getRoot() {
        return root;
    }

    public int getLevelsNumber() {
        return getLevelsNumberRecursively(root);
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private int getLevelsNumberRecursively(Node subTreeRoot) {
        if (subTreeRoot instanceof E) {
            if (!((E) subTreeRoot).getBelow().isPresent()) {
                return 1;
            }
            return getLevelsNumberRecursively(((E) subTreeRoot).getBelow().get()) + 1;
        }

        if (subTreeRoot instanceof I) {
            OptionalInt maxLevels =
                    ((I) subTreeRoot).getLevelNeighbours().stream().mapToInt(this::getLevelsNumberRecursively).max();
            return maxLevels.getAsInt();
        }

        // This statement should be unreachable.
        return 0;
    }

}
