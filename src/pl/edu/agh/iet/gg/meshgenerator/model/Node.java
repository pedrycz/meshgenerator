package pl.edu.agh.iet.gg.meshgenerator.model;

public abstract class Node {
	
	private double x; // x coordinate
	private double y; // y coordinate
	
	public Node(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
