package pl.edu.agh.iet.gg.meshgenerator.model;

public class E extends Node {

	public E() {
		this(0.0, 0.0);
	}

	public E(double x, double y) {
		super(x, y, new Node[9]);
	}

}
