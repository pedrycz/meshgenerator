package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ja on 17.05.17.
 */
public class P2c extends Production{
    private I i;
    private E se;
    private E sw;

    public P2c(CyclicBarrier cyclicBarrier, I i, E se, E sw) {
        super(cyclicBarrier);
        this.i = i;
        this.se = se;
        this.sw = sw;
    }

    @Override
    public void applyProduction() {
        i.applyP2c(se, sw);
    }
}
