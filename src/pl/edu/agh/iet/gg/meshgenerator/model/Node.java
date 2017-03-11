package pl.edu.agh.iet.gg.meshgenerator.model;

public abstract class Node {

    // TODO 2017-03-11 by bgrochal: These fields are not necessary for visualisation purposes. Remove if you don't need them also.
	private double x; // x coordinate
	private double y; // y coordinate

    protected Node[] neighbors;

	
	public Node(double x, double y, Node[] neighbors) {
		this.x = x;
		this.y = y;

        this.neighbors = neighbors;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public Node getNeighbor(Direction direction) {
        return neighbors[direction.getDirectionIndex()];
    }

    public void setNeighbor(Direction direction, Node neighbor) {
        neighbors[direction.getDirectionIndex()] = neighbor;
    }
	
}
