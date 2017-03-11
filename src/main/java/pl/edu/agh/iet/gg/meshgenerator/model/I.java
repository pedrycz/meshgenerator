package pl.edu.agh.iet.gg.meshgenerator.model;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class I extends Node {

    private final E above; // node above
    private final E nw; // node on northwest
    private final E sw; // node on southwest
    private final E se; // node on southeast
    private final E ne; // node on northeast

    private I(E above, E nw, E sw, E se, E ne) {
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

    private void applyP2Horizontally(E western, E eastern) {
        western.getBelow().get().getNe().bidirectionalConnectEast(eastern.getBelow().get().getNw());
        western.getBelow().get().getSe().bidirectionalConnectEast(eastern.getBelow().get().getSw());
        new I(
                western.getBelow().get().getNe(),
                western.getBelow().get().getSe(),
                eastern.getBelow().get().getSw(),
                eastern.getBelow().get().getNw());
    }

    private void applyP2Vertically(E northern, E southern) {
        southern.getBelow().get().getNe().bidirectionalConnectNorth(northern.getBelow().get().getSe());
        southern.getBelow().get().getNw().bidirectionalConnectNorth(northern.getBelow().get().getSw());
        new I(
                northern.getBelow().get().getSw(),
                southern.getBelow().get().getNw(),
                southern.getBelow().get().getNe(),
                southern.getBelow().get().getSe());
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

    public void applyP2a(E western, E eastern) {
        checkState(canApplyP2a(western, eastern));
        applyP2Horizontally(western, eastern);
    }

    public void applyP2b(E northern, E southern) {
        checkState(canApplyP2b(northern, southern));
        applyP2Vertically(northern, southern);
    }

    public void applyP2c(E western, E eastern) {
        checkState(canApplyP2c(western, eastern));
        applyP2Horizontally(western, eastern);
    }

    public void applyP2d(E northern, E southern) {
        checkState(canApplyP2d(northern, southern));
        applyP2Vertically(northern, southern);
    }

    static I createConnectedToNewNodes(E above) {
        E nw = new E();
        E sw = new E();
        E se = new E();
        E ne = new E();

        se.bidirectionalConnectNorth(ne);
        sw.bidirectionalConnectNorth(nw);
        nw.bidirectionalConnectEast(ne);
        sw.bidirectionalConnectEast(se);

        return new I(above, nw, sw, se, ne);
    }

}
