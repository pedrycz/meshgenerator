package pl.edu.agh.iet.gg.meshgenerator.model;

import com.google.common.base.*;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class I extends Node {

    private final E above; // node above
    private final E nw; // node on northwest
    private final E sw; // node on southwest
    private final E se; // node on southeast
    private final E ne; // node on northeast

    private static double getOffsetX(E above, E sw) {
        if (above != null) {
            return above.getOffsetX();
        } else {
            return sw.getOffsetX() + Math.pow(2.0, -(sw.getLevel() - 1));
        }
    }

    private static double getOffsetY(E above, E sw) {
        if (above != null) {
            return above.getOffsetY();
        } else {
            return sw.getOffsetY() + Math.pow(2.0, -(sw.getLevel() - 1));
        }
    }

    private static int getLevel(E above, E sw) {
        if (above != null) {
            return above.getLevel() + 1;
        } else {
            return sw.getLevel();
        }
    }

    private I(E above, E nw, E sw, E se, E ne) {
        super(getOffsetX(above, sw), getOffsetY(above, sw), getLevel(above, sw));
        checkArgument(!nw.isConnectedSE());
        checkArgument(!sw.isConnectedNE());
        checkArgument(!se.isConnectedNW());
        checkArgument(!ne.isConnectedSW());
        this.above = above;
        this.nw = nw;
        this.sw = sw;
        this.se = se;
        this.ne = ne;
        if (above != null) {
            above.setBelow(this);
        }
        nw.setSE(this);
        sw.setNE(this);
        se.setNW(this);
        ne.setSW(this);
    }


    public Optional<E> getAbove() {
        return Optional.ofNullable(above);
    }

    public E getNW() {
        return nw;
    }

    public E getSW() {
        return sw;
    }

    public E getSE() {
        return se;
    }

    public E getNE() {
        return ne;
    }

    private boolean canApplyP2Horizontal(E western, E eastern) {
        return western.isExpanded() &&
                eastern.isExpanded() &&
                !western.getBelow().get().getNE().isConnectedE() &&
                !western.getBelow().get().getSE().isConnectedE() &&
                !eastern.getBelow().get().getNW().isConnectedW() &&
                !eastern.getBelow().get().getSW().isConnectedW();

    }

    private boolean canApplyP2Vertical(E northern, E southern) {
        return southern.isExpanded() &&
                northern.isExpanded() &&
                !southern.getBelow().get().getNE().isConnectedN() &&
                !southern.getBelow().get().getNW().isConnectedN() &&
                !northern.getBelow().get().getSE().isConnectedS() &&
                !northern.getBelow().get().getSW().isConnectedS();
    }

    private ProductionResults applyP2Horizontally(E western, E eastern) {
        ProductionResults pr1 = western.getBelow().get().getNE().bidirectionalConnectE(eastern.getBelow().get().getNW());
        ProductionResults pr2 = western.getBelow().get().getSE().bidirectionalConnectE(eastern.getBelow().get().getSW());
        ProductionResults pr3 = I.createConnectedToExistingNodesAndGetChanges(
                western.getBelow().get().getNE(),
                western.getBelow().get().getSE(),
                eastern.getBelow().get().getSW(),
                eastern.getBelow().get().getNW());

        return ProductionResults.merge(pr1, pr2, pr3);
    }

    private ProductionResults applyP2Vertically(E northern, E southern) {
        ProductionResults pr1 = southern.getBelow().get().getNE().bidirectionalConnectN(northern.getBelow().get().getSE());
        ProductionResults pr2 = southern.getBelow().get().getNW().bidirectionalConnectN(northern.getBelow().get().getSW());
        ProductionResults pr3 = I.createConnectedToExistingNodesAndGetChanges(
                northern.getBelow().get().getSW(),
                southern.getBelow().get().getNW(),
                southern.getBelow().get().getNE(),
                northern.getBelow().get().getSE());

        return ProductionResults.merge(pr1, pr2, pr3);
    }

    public boolean canApplyP2a(E western, E eastern) {
        return eastern.equals(ne) &&
                western.equals(nw) &&
                canApplyP2Horizontal(western, eastern);
    }

    public boolean canApplyP2b(E northern, E southern) {
        return northern.equals(nw) &&
                southern.equals(sw) &&
                canApplyP2Vertical(northern, southern);
    }

    public boolean canApplyP2c(E western, E eastern) {
        return eastern.equals(se) &&
                western.equals(sw) &&
                canApplyP2Horizontal(western, eastern);
    }

    public boolean canApplyP2d(E northern, E southern) {
        return northern.equals(ne) &&
                southern.equals(se) &&
                canApplyP2Vertical(northern, southern);
    }

    public ProductionResults applyP2a(E western, E eastern) {
        checkState(canApplyP2a(western, eastern));
        return applyP2Horizontally(western, eastern);
    }

    public ProductionResults applyP2b(E northern, E southern) {
        checkState(canApplyP2b(northern, southern));
        return applyP2Vertically(northern, southern);
    }

    public ProductionResults applyP2c(E western, E eastern) {
        checkState(canApplyP2c(western, eastern));
        return applyP2Horizontally(western, eastern);
    }

    public ProductionResults applyP2d(E northern, E southern) {
        checkState(canApplyP2d(northern, southern));
        return applyP2Vertically(northern, southern);
    }

    public List<E> getLevelNeighbours() {
        return Arrays.asList(getNW(), getSW(), getSE(), getNE());
    }


    private static ProductionResults createConnectedToExistingNodesAndGetChanges(E above, E nw, E sw, E se, E ne) {
        checkArgument(sw.getE().map(x -> x.equals(se)).orElseGet(() -> false));
        checkArgument(se.getN().map(x -> x.equals(ne)).orElseGet(() -> false));
        checkArgument(ne.getW().map(x -> x.equals(nw)).orElseGet(() -> false));
        checkArgument(nw.getS().map(x -> x.equals(sw)).orElseGet(() -> false));
        I i = new I(above, nw, sw, se, ne);
        List<Node> nodes = Collections.singletonList(i);
        List<Edge> edges = Lists.newArrayList(
                new Edge(nw, i),
                new Edge(sw, i),
                new Edge(se, i),
                new Edge(ne, i)
        );
        if (above != null) {
            edges.add(new Edge(above, i));
        }
        return new ProductionResults(nodes, edges);
    }

    static ProductionResults createConnectedToExistingNodesAndGetChanges(E nw, E sw, E se, E ne) {
        return createConnectedToExistingNodesAndGetChanges(null, nw, sw, se, ne);
    }

    static ProductionResults createConnectedToNewNodesAndGetChanges(E above) {
        int level = above.getLevel() + 1;
        double aboveOffsetX = above.getOffsetX();
        double aboveOffsetY = above.getOffsetY();
        double offsetDelta = Math.pow(2.0, -above.getLevel());

        E nw = new E(aboveOffsetX + offsetDelta, aboveOffsetY - offsetDelta, level);
        E sw = new E(aboveOffsetX - offsetDelta, aboveOffsetY - offsetDelta, level);
        E se = new E(aboveOffsetX - offsetDelta, aboveOffsetY + offsetDelta, level);
        E ne = new E(aboveOffsetX + offsetDelta, aboveOffsetY + offsetDelta, level);

        ProductionResults pr1 = se.bidirectionalConnectN(ne);
        ProductionResults pr2 = sw.bidirectionalConnectN(nw);
        ProductionResults pr3 = nw.bidirectionalConnectE(ne);
        ProductionResults pr4 = sw.bidirectionalConnectE(se);
        ProductionResults pr5 = I.createConnectedToExistingNodesAndGetChanges(above, nw, sw, se, ne);
        ProductionResults pr6 = new ProductionResults(Lists.newArrayList(nw, sw, se, ne), Collections.emptyList());

        return ProductionResults.merge(pr1, pr2, pr3, pr4, pr5, pr6);
    }

    @Override
    public List<Node> getAllChildren() {
        List<Node> nodes = new ArrayList<>();
        nodes.addAll(this.nw.getAllChildren());
        nodes.addAll(this.ne.getAllChildren());
        nodes.addAll(this.se.getAllChildren());
        nodes.addAll(this.sw.getAllChildren());
        return nodes;
    }

    @Override
    public List<Edge> getAllEdges() {
        List<Optional<?>> edges = new ArrayList<>();
        edges.add(this.getAbove());
        edges.add(Optional.ofNullable(this.getNE()));
        edges.add(Optional.ofNullable(this.getNW()));
        edges.add(Optional.ofNullable(this.getSE()));
        edges.add(Optional.ofNullable(this.getSW()));
        Stream<?> streamOfEdges = edges.stream()
                .filter(Optional::isPresent).map(Optional::get);
        List<Edge> listOfEdges = streamOfEdges
                .map(vertex -> new Edge(this, (Node) vertex))
                .collect(Collectors.toList());
        streamOfEdges
                .forEach(edge -> listOfEdges.addAll(((Node) edge).getAllEdges()));
        return listOfEdges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        I i = (I) o;

        if (above != null ? !above.equals(i.above) : i.above != null) return false;
        if (nw != null ? !nw.equals(i.nw) : i.nw != null) return false;
        if (sw != null ? !sw.equals(i.sw) : i.sw != null) return false;
        if (se != null ? !se.equals(i.se) : i.se != null) return false;
        return ne != null ? ne.equals(i.ne) : i.ne == null;

    }

    @Override
    public int hashCode() {
        int result = above != null ? above.hashCode() : 0;
        result = 31 * result + (nw != null ? nw.hashCode() : 0);
        result = 31 * result + (sw != null ? sw.hashCode() : 0);
        result = 31 * result + (se != null ? se.hashCode() : 0);
        result = 31 * result + (ne != null ? ne.hashCode() : 0);
        return result;
    }
}
