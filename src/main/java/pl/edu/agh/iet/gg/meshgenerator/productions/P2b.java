package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public class P2b extends Production {

    private I i;
    private E nw;
    private E sw;

    public P2b(CyclicBarrier cyclicBarrier, I i, E nw, E sw) {
        super(cyclicBarrier);
        this.i = i;
        this.nw = nw;
        this.sw = sw;
    }

    @Override
    public void applyProduction() {
        i.applyP2b(nw, sw);
    }
}
