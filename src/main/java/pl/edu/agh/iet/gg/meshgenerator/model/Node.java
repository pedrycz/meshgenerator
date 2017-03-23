package pl.edu.agh.iet.gg.meshgenerator.model;


public abstract class Node {
    private final int offsetX;
    private final int offsetY;
    private final int level;

    public Node(int offsetX, int offsetY, int level) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.level = level;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
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
