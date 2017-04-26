package pl.edu.agh.iet.gg.meshgenerator.model;


public abstract class Node {
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

    @Override
    public String toString() {
        return "Node(" + offsetX + ", " + offsetY + ", " + level + ")";
    }
}
