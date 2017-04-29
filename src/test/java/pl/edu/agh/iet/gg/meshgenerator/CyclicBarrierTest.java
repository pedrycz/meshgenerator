package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.productions.P1;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public class CyclicBarrierTest {

    @Test
    public void cyclicBarrierShouldThrowNoError() throws Exception {

        Graph graph = new Graph();

        // level 0

        E e = graph.getRoot();

        CyclicBarrier barrier1 = new CyclicBarrier(1+1);
        P1 p1 = new P1(barrier1, e);
        p1.start();
        barrier1.await();

        //level 1

        E nw = e.getBelow().get().getNW();
        E ne = e.getBelow().get().getNE();
        E se = e.getBelow().get().getSE();
        E sw = e.getBelow().get().getSW();

        CyclicBarrier barrier2 = new CyclicBarrier(4+1);
        P1 p1nw = new P1(barrier2, nw);
        P1 p1ne = new P1(barrier2, ne);
        P1 p1se = new P1(barrier2, se);
        P1 p1sw = new P1(barrier2, sw);
        p1nw.start();
        p1ne.start();
        p1se.start();
        p1sw.start();
        barrier2.await();

    }

}
