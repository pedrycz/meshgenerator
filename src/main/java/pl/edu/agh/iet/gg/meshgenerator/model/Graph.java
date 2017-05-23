package pl.edu.agh.iet.gg.meshgenerator.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

/**
 * @author Wojciech Pachuta.
 */
public class Graph implements Serializable{

    private E root;
    private Set<Edge> edges;
    private Set<Node> vertex;

    private static final Graph INSTANCE = new Graph();

    public static Graph getInstance() {
        return INSTANCE;
    }

    private Graph(){
        this.root = new E(0,0,0);
        this.edges = new HashSet<>();
        this.vertex = new HashSet<>();
        this.vertex.add(root);
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

    public void addEdges(List<Edge> edge){
        this.edges.addAll(edge);
    }

    public void addVertexes(List<Node> vertex){
        this.vertex.addAll(vertex);
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public Set<Node> getVertex() {
        return vertex;
    }
}
