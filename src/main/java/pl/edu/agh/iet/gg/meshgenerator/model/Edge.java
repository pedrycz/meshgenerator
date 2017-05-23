package pl.edu.agh.iet.gg.meshgenerator.model;

import java.io.Serializable;

/**
 * @author Wojciech Pachuta.
 */
public class Edge implements Serializable {
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

    public boolean isEqual(Edge edge) {
        return (edge.a.equals(this.b) && edge.b.equals(this.a)) || (edge.a.equals(this.a) && edge.b.equals(this.b));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

       return (edge.a.equals(this.b) && edge.b.equals(this.a)) || (edge.a.equals(this.a) && edge.b.equals(this.b));

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Edge(" + a.toString() + ", " + b.toString() + ")";
    }
}
