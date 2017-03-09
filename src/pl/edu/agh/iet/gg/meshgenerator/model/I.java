package pl.edu.agh.iet.gg.meshgenerator.model;

public class I extends Node {

	private E t; // node on top
	private E nw; // node on northwest
	private E sw; // node on southwest
	private E se; // node on southeast
	private E ne; // node on northeast

	public I(double x, double y) {
		super(x, y);
	}
	
	public E getT() {
		return t;
	}

	public E getNw() {
		return nw;
	}

	public E getSw() {
		return sw;
	}

	public E getSe() {
		return se;
	}

	public E getNe() {
		return ne;
	}

	public void setT(E t) {
		this.t = t;
	}

	public void setNw(E nw) {
		this.nw = nw;
	}

	public void setSw(E sw) {
		this.sw = sw;
	}

	public void setSe(E se) {
		this.se = se;
	}

	public void setNe(E ne) {
		this.ne = ne;
	}

}
