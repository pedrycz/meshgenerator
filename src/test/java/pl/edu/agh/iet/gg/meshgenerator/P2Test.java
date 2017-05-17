package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.model.I;

/**
 * Created by ppedrycz
 */
public class P2Test {

    @Test
    public void productionP2aShouldJoinNodesHorizontally() throws Exception {
        //given
        Graph graph = new Graph();
        graph.getRoot().applyP1();
        I i = graph.getRoot().getBelow().get();

        //when
        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNW(), i.getNE());

        //then
        assert(i.getNW().getBelow().get().getNE().getSE().equals(i.getNE().getBelow().get().getNW().getSW()));
        assert(i.getNW().getBelow().get().getSE().getNE().equals(i.getNE().getBelow().get().getSW().getNW()));
        assert(i.getNW().getBelow().get().getNE().getE().get().equals(i.getNE().getBelow().get().getNW()));
        assert(i.getNW().getBelow().get().getSE().getE().get().equals(i.getNE().getBelow().get().getSW()));

    }

    @Test
    public void productionP2bShouldJoinNodesVertically() throws Exception {
        //given
        Graph graph = new Graph();
        graph.getRoot().applyP1();
        I i = graph.getRoot().getBelow().get();

        //when
        i.getNW().applyP1();
        i.getSW().applyP1();
        i.applyP2b(i.getNW(), i.getSW());

        //then
        assert(i.getNW().getBelow().get().getSE().getSW().equals(i.getSW().getBelow().get().getNE().getNW()));
        assert(i.getNW().getBelow().get().getSW().getSE().equals(i.getSW().getBelow().get().getNW().getNE()));
        assert(i.getNW().getBelow().get().getSE().getS().get().equals(i.getSW().getBelow().get().getNE()));
        assert(i.getNW().getBelow().get().getSW().getS().get().equals(i.getSW().getBelow().get().getNW()));
    }

    @Test
    public void productionP2cShouldJoinNodesHorizontally() throws Exception {
        //given
        Graph graph = new Graph();
        graph.getRoot().applyP1();
        I i = graph.getRoot().getBelow().get();

        //when
        i.getSW().applyP1();
        i.getSE().applyP1();
        i.applyP2c(i.getSW(), i.getSE());

        //then
        assert(i.getSW().getBelow().get().getSE().getNE().equals(i.getSE().getBelow().get().getSW().getNW()));
        assert(i.getSW().getBelow().get().getNE().getSE().equals(i.getSE().getBelow().get().getNW().getSW()));
        assert(i.getSW().getBelow().get().getNE().getE().get().equals(i.getSE().getBelow().get().getNW()));
        assert(i.getSW().getBelow().get().getSE().getE().get().equals(i.getSE().getBelow().get().getSW()));
    }

    @Test
    public void productionP2dShouldJoinNodesVertically() throws Exception {
        //given
        Graph graph = new Graph();
        graph.getRoot().applyP1();
        I i = graph.getRoot().getBelow().get();

        //when
        i.getNE().applyP1();
        i.getSE().applyP1();
        i.applyP2d(i.getNE(), i.getSE());

        //then
        assert(i.getNE().getBelow().get().getSW().getSE().equals(i.getSE().getBelow().get().getNW().getNE()));
        assert(i.getNE().getBelow().get().getSE().getSW().equals(i.getSE().getBelow().get().getNE().getNW()));
        assert(i.getNE().getBelow().get().getSE().getS().get().equals(i.getSE().getBelow().get().getNE()));
        assert(i.getNE().getBelow().get().getSW().getS().get().equals(i.getSE().getBelow().get().getNW()));

    }

}
