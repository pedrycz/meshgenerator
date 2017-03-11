package pl.edu.agh.iet.gg.meshgenerator.model;

public enum Direction {

    TOP(0), BOTTOM(0), NW(1), SW(2), SE(3), NE(4), N(5), W(6), E(7), S(8);


    private final int directionIndex;

    Direction(int directionIndex) {
        this.directionIndex = directionIndex;
    }


    public int getDirectionIndex() {
        return directionIndex;
    }

}
