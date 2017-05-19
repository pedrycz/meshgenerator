package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ja on 17.05.17.
 */
public class P2d  extends Production{
    private I i;
    private E ne;
    private E se;

    public P2d(CyclicBarrier cyclicBarrier, I i, E ne, E se) {
        super(cyclicBarrier);
        this.i = i;
        this.ne = ne;
        this.se = se;
    }

    @Override
    public void applyProduction() {
        i.applyP2d(ne, se);
    }
}