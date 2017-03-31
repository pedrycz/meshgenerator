package pl.edu.agh.iet.gg.meshgenerator.visualization.util.view;

/**
 * @author Bartłomiej Grochal
 */
public enum RotateDirection {
    RIGHT(-1), LEFT(1), UP(1), DOWN(-1);


    private int directionSign;


    RotateDirection(int directionSign) {
        this.directionSign = directionSign;
    }


    public int getDirectionSign() {
        return directionSign;
    }

}
