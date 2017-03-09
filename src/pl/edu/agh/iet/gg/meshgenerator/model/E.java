package pl.edu.agh.iet.gg.meshgenerator.model;

public class E extends Node {

	private I b; // node on bottom
	private E n; // node on north
	private E w; // node on west
	private E s; // node on south
	private E e; // node on east
	private I nw; // node on northwest
	private I sw; // node on southwest
	private I se; // node on southeast
	private I ne; // node on northeast

	public E(double x, double y) {
		super(x, y);
	}
	
	public I getB() {
		return b;
	}

	public E getN() {
		return n;
	}

	public E getW() {
		return w;
	}

	public E getS() {
		return s;
	}

	public E getE() {
		return e;
	}

	public I getNw() {
		return nw;
	}

	public I getSw() {
		return sw;
	}

	public I getSe() {
		return se;
	}

	public I getNe() {
		return ne;
	}

	public void setB(I b) {
		this.b = b;
	}

	public void setN(E n) {
		this.n = n;
	}

	public void setW(E w) {
		this.w = w;
	}

	public void setS(E s) {
		this.s = s;
	}

	public void setE(E e) {
		this.e = e;
	}

	public void setNw(I nw) {
		this.nw = nw;
	}

	public void setSw(I sw) {
		this.sw = sw;
	}

	public void setSe(I se) {
		this.se = se;
	}

	public void setNe(I ne) {
		this.ne = ne;
	}

}
