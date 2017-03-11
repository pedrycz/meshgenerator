package pl.edu.agh.iet.gg.meshgenerator.model;


import pl.edu.agh.iet.gg.meshgenerator.util.assertion.Assert;

import static com.google.common.base.Preconditions.checkArgument;

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
		checkArgument(true);
		Assert.isIndexInBounds(direction.getDirectionIndex(), neighbors.length);
		return neighbors[direction.getDirectionIndex()];
    }

    public I getNeighborAsI(Direction direction) {
		Node neighbor = getNeighbor(direction);
		Assert.isInstance(neighbor, I.class);
		return (I) neighbor;
	}

	public E getNeighborAsE(Direction direction) {
		Node neighbor = getNeighbor(direction);
		Assert.isInstance(neighbor, E.class);
		return (E) neighbor;
	}

    public void setNeighbor(Direction direction, Node neighbor) {
		Assert.isIndexInBounds(direction.getDirectionIndex(), neighbors.length);
        neighbors[direction.getDirectionIndex()] = neighbor;
    }
	
}
