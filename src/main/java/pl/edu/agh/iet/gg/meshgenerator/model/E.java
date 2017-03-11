package pl.edu.agh.iet.gg.meshgenerator.model;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

public class E extends Node {

    private I below; // node below
    private E n; // node on north
    private E w; // node on west
    private E s; // node on south
    private E e; // node on east
    private I nw; // node on northwest
    private I sw; // node on southwest
    private I se; // node on southeast
    private I ne; // node on northeast

    E() {
    }

    public Optional<I> getBelow() {
        return Optional.ofNullable(below);
    }

    public Optional<E> getN() {
        return Optional.ofNullable(n);
    }

    public Optional<E> getW() {
        return Optional.ofNullable(w);
    }

    public Optional<E> getS() {
        return Optional.ofNullable(s);
    }

    public Optional<E> getE() {
        return Optional.ofNullable(e);
    }

    public Optional<I> getNw() {
        return Optional.ofNullable(nw);
    }

    public Optional<I> getSw() {
        return Optional.ofNullable(sw);
    }

    public Optional<I> getSe() {
        return Optional.ofNullable(se);
    }

    public Optional<I> getNe() {
        return Optional.ofNullable(ne);
    }

    void setBelow(I below) {
        this.below = below;
    }

    void setNw(I nw) {
        this.nw = nw;
    }

    void setSw(I sw) {
        this.sw = sw;
    }

    void setSe(I se) {
        this.se = se;
    }

    void setNe(I ne) {
        this.ne = ne;
    }

    boolean isExpanded() {
        return below != null;
    }

    boolean isConnectedNorth() {
        return n != null;
    }

    boolean isConnectedSouth() {
        return s != null;
    }

    boolean isConnectedEast() {
        return e != null;
    }

    boolean isConnectedWest() {
        return w != null;
    }

    boolean isConnectedNW() {
        return nw != null;
    }

    boolean isConnectedSW() {
        return sw != null;
    }

    boolean isConnectedSE() {
        return se != null;
    }

    boolean isConnectedNE() {
        return ne != null;
    }

    void bidirectionalConnectNorth(E northern) {
        checkState(n == null);
        checkState(northern.s == null);
        n = northern;
        northern.s = this;
    }

    void bidirectionalConnectEast(E eastern) {
        checkState(e == null);
        checkState(eastern.w == null);
        e = eastern;
        eastern.w = this;
    }

    public boolean canApplyP1() {
        return !isExpanded();
    }

    public void applyP1() {
        I.createConnectedToNewNodes(this);
    }

}
