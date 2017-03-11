package pl.edu.agh.iet.gg.meshgenerator.model;

public class I extends Node {

	public I() {
		this(0.0, 0.0);
	}

	public I(double x, double y) {
		super(x, y, new Node[5]);
	}

}
