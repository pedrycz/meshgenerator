package pl.edu.agh.iet.gg.meshgenerator.model;

/**
 * @author Wojciech Pachuta.
 */
public class Edge {
    private final Node a;
    private final Node b;

    public Edge(Node a, Node b) {
        this.a = a;
        this.b = b;
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Edge(" + a.toString() + ", " + b.toString()+ ")";
    }
}
