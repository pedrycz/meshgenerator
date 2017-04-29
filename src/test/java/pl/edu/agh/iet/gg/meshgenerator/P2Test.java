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
    }

}
