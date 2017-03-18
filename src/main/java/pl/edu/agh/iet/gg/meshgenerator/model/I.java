package pl.edu.agh.iet.gg.meshgenerator.model;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class I extends Node {

    private final E above; // node above
    private final E nw; // node on northwest
    private final E sw; // node on southwest
    private final E se; // node on southeast
    private final E ne; // node on northeast

    private static int getOffsetX(E above, E sw) {
        if (above != null) {
            return above.getOffsetX() * 2;
        } else {
            return sw.getOffsetX() + 1;
        }
    }

    private static int getOffsetY(E above, E sw) {
        if (above != null) {
            return above.getOffsetY() * 2;
        } else {
            return sw.getOffsetY() + 1;
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
        nw.setSe(this);
        sw.setNe(this);
        se.setNw(this);
        ne.setSw(this);
    }

    private I(E nw, E sw, E se, E ne) {
        this(null, nw, sw, se, ne);
    }

    public Optional<E> getAbove() {
        return Optional.ofNullable(above);
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

    private boolean canApplyP2Horizontal(E western, E eastern) {
        return western.isExpanded() &&
                eastern.isExpanded() &&
                !western.getBelow().get().getNe().isConnectedEast() &&
                !western.getBelow().get().getSe().isConnectedEast() &&
                !eastern.getBelow().get().getNw().isConnectedWest() &&
                !eastern.getBelow().get().getSw().isConnectedWest();
    }

    private boolean canApplyP2Vertical(E northern, E southern) {
        return southern.isExpanded() &&
                northern.isExpanded() &&
                !southern.getBelow().get().getNe().isConnectedNorth() &&
                !southern.getBelow().get().getNw().isConnectedNorth() &&
                !northern.getBelow().get().getSe().isConnectedSouth() &&
                !northern.getBelow().get().getSw().isConnectedSouth();
    }

    private ProductionResults applyP2Horizontally(E western, E eastern) {
        ProductionResults pr1 = western.getBelow().get().getNe().bidirectionalConnectEast(eastern.getBelow().get().getNw());
        ProductionResults pr2 = western.getBelow().get().getSe().bidirectionalConnectEast(eastern.getBelow().get().getSw());
        ProductionResults pr3 = I.createConnectedToExistingNodesAndGetChanges(
                western.getBelow().get().getNe(),
                western.getBelow().get().getSe(),
                eastern.getBelow().get().getSw(),
                eastern.getBelow().get().getNw());

        return ProductionResults.merge(pr1, pr2, pr3);
    }

    private ProductionResults applyP2Vertically(E northern, E southern) {
        ProductionResults pr1 = southern.getBelow().get().getNe().bidirectionalConnectNorth(northern.getBelow().get().getSe());
        ProductionResults pr2 = southern.getBelow().get().getNw().bidirectionalConnectNorth(northern.getBelow().get().getSw());
        ProductionResults pr3 = I.createConnectedToExistingNodesAndGetChanges(
                northern.getBelow().get().getSw(),
                southern.getBelow().get().getNw(),
                southern.getBelow().get().getNe(),
                southern.getBelow().get().getSe());

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
        int iOffxetX = above.getOffsetX() * 2;
        int iOffxetY = above.getOffsetY() * 2;

        E nw = new E(iOffxetX + 1, iOffxetY - 1, above.getLevel() + 1);
        E sw = new E(iOffxetX - 1, iOffxetY - 1, above.getLevel() + 1);
        E se = new E(iOffxetX - 1, iOffxetY + 1, above.getLevel() + 1);
        E ne = new E(iOffxetX + 1, iOffxetY + 1, above.getLevel() + 1);

        ProductionResults pr1 = se.bidirectionalConnectNorth(ne);
        ProductionResults pr2 = sw.bidirectionalConnectNorth(nw);
        ProductionResults pr3 = nw.bidirectionalConnectEast(ne);
        ProductionResults pr4 = sw.bidirectionalConnectEast(se);
        ProductionResults pr5 = I.createConnectedToExistingNodesAndGetChanges(above, nw, sw, se, ne);
        ProductionResults pr6 = new ProductionResults(Lists.newArrayList(nw, sw, se, ne), Collections.emptyList());

        return ProductionResults.merge(pr1, pr2, pr3, pr4, pr5, pr6);
    }

}
