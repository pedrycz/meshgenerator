package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.productions.*;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public class CyclicBarrierTest {

    @Test
    public void cyclicBarrierShouldThrowNoError() throws Exception {

        Graph graph = Graph.getInstance();

        // level 0 create

        E e = graph.getRoot();

        CyclicBarrier barrier1 = new CyclicBarrier(1+1);
        P1 p1 = new P1(barrier1, e);
        p1.start();
        barrier1.await();

        //level 1 create

        I i = e.getBelow().get();

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

        // level 1 merge

        CyclicBarrier barrier3 = new CyclicBarrier(4+1);
        P2a p2a = new P2a(barrier3, i, nw, ne);
        P2b p2b = new P2b(barrier3, i, nw, sw);
        P2c p2c = new P2c(barrier3, i, sw, se);
        P2d p2d = new P2d(barrier3, i, ne, se);
        p2a.start();
        p2b.start();
        p2c.start();
        p2d.start();
        barrier3.await();

    }

}
