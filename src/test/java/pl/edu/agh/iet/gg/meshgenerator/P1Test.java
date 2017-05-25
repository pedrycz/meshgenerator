package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;

/**
 * Created by rgrabianski
 */
public class P1Test {

    E e;

    private E prepareEmptyGraph() {
        Graph graph = Graph.getInstance();
        return graph.getRoot();
    }

    @Before
    public void prepareGraph() {
        e = prepareEmptyGraph();
        e.applyP1();
    }

    @Test
    public void productionP1ShouldCreateVerticalNodeBelow() throws Exception {

        assert e.getBelow().isPresent();
        assert e.getBelow().get().getAbove().get().equals(e);
    }

    @Test
    public void productionP1ShouldJoinCreatedNodesHorizontally() throws Exception {

        assert e.getBelow().isPresent();
        assert e.getBelow().get().getLevelNeighbours().size() == 4;
        assert e.getAllChildren(false).size() == 5;
    }

    @Test
    public void nodesFromP1ProductionShouldAllowApplyingP1ExceptCenterNode() throws Exception {

        assert e.getBelow().isPresent();
        assert e.getBelow().get().getNW().canApplyP1();
        assert e.getBelow().get().getSE().canApplyP1();
        assert e.getBelow().get().getNE().canApplyP1();
        assert e.getBelow().get().getSW().canApplyP1();

        assert e.getBelow().get().getNW().applyP1().getAddedNodes().size() == 5;
        assert e.getBelow().get().getNE().applyP1().getAddedNodes().size() == 5;
        assert e.getBelow().get().getSE().applyP1().getAddedNodes().size() == 5;
        assert e.getBelow().get().getSW().applyP1().getAddedNodes().size() == 5;

    }

    @Test
    public void productionP1ShouldCreateRightConnections() throws Exception{

        assert e.getBelow().isPresent();
        assert e.getBelow().get().getNE().getW().get().equals(e.getBelow().get().getNW());
        assert e.getBelow().get().getNE().getS().get().equals(e.getBelow().get().getSE());
        assert e.getBelow().get().getSE().getW().get().equals(e.getBelow().get().getSW());
        assert e.getBelow().get().getSE().getN().get().equals(e.getBelow().get().getNE());
        assert e.getBelow().get().getNW().getS().get().equals(e.getBelow().get().getSW());
        assert e.getBelow().get().getNW().getE().get().equals(e.getBelow().get().getNE());
        assert e.getBelow().get().getSW().getN().get().equals(e.getBelow().get().getNW());
        assert e.getBelow().get().getSW().getE().get().equals(e.getBelow().get().getSE());


    }

    @Test
    public void productionP1ShouldProduceGoodLevels() throws Exception {
        assert e.getBelow().get().getLevel() == 1;
        e.getBelow().get().getSW().applyP1();
        assert e.getBelow().get().getSW().getBelow().get().getLevel() == 2;
    }

    @Test
    public void productionP1ShouldProduceRightNumberOfChildren() {
        e.getBelow().get().getSW().applyP1();
        e.getBelow().get().getNW().applyP1();
        e.getBelow().get().getNE().applyP1();
        e.getBelow().get().getSE().applyP1();
        assert e.getAllChildren(false).size() == 25;
    }


}
