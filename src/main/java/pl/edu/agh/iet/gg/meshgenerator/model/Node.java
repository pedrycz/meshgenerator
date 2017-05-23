package pl.edu.agh.iet.gg.meshgenerator.model;


import java.io.Serializable;
import java.util.List;

public abstract class Node implements Serializable {
    private final double offsetX;
    private final double offsetY;
    private final int level;

    public Node(double offsetX, double offsetY, int level) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.level = level;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public int getLevel() {
        return level;
    }

    public abstract List<Node> getAllChildren();

    public abstract List<Edge> getAllEdges();


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.offsetX, offsetX) != 0) return false;
        if (Double.compare(node.offsetY, offsetY) != 0) return false;
        return level == node.level;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(offsetX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(offsetY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Node(" + offsetX + ", " + offsetY + ", " + level + ")";
    }
}
