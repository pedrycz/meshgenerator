package pl.edu.agh.iet.gg.meshgenerator.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    E(double offsetX, double offsetY, int level) {
        super(offsetX, offsetY, level);
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

    public Optional<I> getNW() {
        return Optional.ofNullable(nw);
    }

    public Optional<I> getSW() {
        return Optional.ofNullable(sw);
    }

    public Optional<I> getSE() {
        return Optional.ofNullable(se);
    }

    public Optional<I> getNE() {
        return Optional.ofNullable(ne);
    }

    public boolean isExpanded() {
        return below != null;
    }

    void setBelow(I below) {
        this.below = below;
    }

    void setNW(I nw) {
        this.nw = nw;
    }

    void setSW(I sw) {
        this.sw = sw;
    }

    void setSE(I se) {
        this.se = se;
    }

    void setNE(I ne) {
        this.ne = ne;
    }

    boolean isConnectedN() {
        return n != null;
    }

    boolean isConnectedS() {
        return s != null;
    }

    boolean isConnectedE() {
        return e != null;
    }

    boolean isConnectedW() {
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

    ProductionResults bidirectionalConnectN(E northern) {
        checkState(n == null);
        checkState(northern.s == null);
        n = northern;
        northern.s = this;
        return new ProductionResults(Collections.singletonList(new Edge(this, northern)));
    }

    ProductionResults bidirectionalConnectE(E eastern) {
        checkState(e == null);
        checkState(eastern.w == null);
        e = eastern;
        eastern.w = this;
        return new ProductionResults(Collections.singletonList(new Edge(this, eastern)));
    }

    public boolean canApplyP1() {
        return !isExpanded();
    }

    public ProductionResults applyP1() {
        return I.createConnectedToNewNodesAndGetChanges(this);
    }

    public static boolean canApplyP3(I node) {

        boolean expanded = node.getSW().isExpanded() &&
                node.getNW().isExpanded() &&
                node.getNE().isExpanded() &&
                node.getSE().isExpanded();

        return expanded &&
                // all required P2-level productions had been applied
                node.getSW().getBelow().get().getNE().isConnectedN() &&
                node.getSW().getBelow().get().getNE().isConnectedE() &&
                node.getNE().getBelow().get().getSW().isConnectedW() &&
                node.getNE().getBelow().get().getSW().isConnectedS() &&
                // P3 itself had not been applied
                !node.getSW().getBelow().get().getNE().isConnectedNE() &&
                !node.getSE().getBelow().get().getNW().isConnectedNW() &&
                !node.getNW().getBelow().get().getSE().isConnectedSE() &&
                !node.getNE().getBelow().get().getSW().isConnectedSW();
    }


    public static ProductionResults applyP3(E e1, E e2, E e3, E e4) {
        List<E> sorted = Arrays.asList(e1, e2, e3, e4);
        sorted.sort(Comparator.comparing(E::getOffsetX).thenComparing(E::getOffsetY));
        E sw = sorted.get(0);
        E se = sorted.get(1);
        E nw = sorted.get(2);
        E ne = sorted.get(3);

        //important
        checkState(sw.getE().map(x -> x.equals(se)).orElseGet(() -> false));
        checkState(se.getN().map(x -> x.equals(ne)).orElseGet(() -> false));
        checkState(ne.getW().map(x -> x.equals(nw)).orElseGet(() -> false));
        checkState(nw.getS().map(x -> x.equals(sw)).orElseGet(() -> false));

        //just in case
        checkState(sw.getN().map(x -> x.equals(nw)).orElseGet(() -> false));
        checkState(nw.getE().map(x -> x.equals(ne)).orElseGet(() -> false));
        checkState(ne.getS().map(x -> x.equals(se)).orElseGet(() -> false));
        checkState(se.getW().map(x -> x.equals(sw)).orElseGet(() -> false));

        return I.createConnectedToExistingNodesAndGetChanges(nw, sw, se, ne);
    }

    @Override
    public List<Node> getAllChildren(boolean isAbove) {
        List<Node> nodes = new ArrayList<>();
        if (this.e != null) {
            nodes.addAll(this.e.getAllChildrenNode());
        }
        if (this.n != null) {
            nodes.addAll(this.n.getAllChildrenNode());
        }
        if (this.w != null) {
            nodes.addAll(this.w.getAllChildrenNode());
        }
        if (this.s != null) {
            nodes.addAll(this.s.getAllChildrenNode());
        }
        if (this.below != null) {
            nodes.addAll(this.below.getAllChildren(true));
        }
        if (this.nw != null) {

            nodes.addAll(this.nw.getAllChildren(false));
        }
        if (this.ne != null) {

            nodes.addAll(this.ne.getAllChildren(false));
        }
        if (this.se != null) {
            nodes.addAll(this.se.getAllChildren(false));
        }
        if (this.sw != null) {
            nodes.addAll(this.sw.getAllChildren(false));
        }
        return nodes;

    }

    public List<Node> getAllChildrenNode() {
        List<Node> nodes = new ArrayList<>();
        if (this.below != null) {
            nodes.addAll(this.below.getAllChildren(true));
        }
        return nodes;
    }

    @Override
    public List<Edge> getAllEdges() {
        List<Optional<?>> edges = new ArrayList<>();
        edges.add(this.getE());
        edges.add(this.getN());
        edges.add(this.getNE());
        edges.add(this.getBelow());
        edges.add(this.getNW());
        edges.add(this.getS());
        edges.add(this.getSE());
        edges.add(this.getSW());
        edges.add(this.getW());
        Stream<?> streamOfEdges = edges.stream()
                .filter(Optional::isPresent).map(Optional::get);
        List<Edge> listOfEdges = streamOfEdges
                .map(vertex -> new Edge(this, (Node) vertex))
                .collect(Collectors.toList());
        streamOfEdges
                .forEach(edge -> listOfEdges.addAll(((Node) edge).getAllEdges()));
        return listOfEdges;

    }
}
