package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.E;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public class P1 extends Production {

    private E e;

    public P1(CyclicBarrier cyclicBarrier, E e) {
        super(cyclicBarrier);
        this.e = e;
    }

    @Override
    public void applyProduction() {
        e.applyP1();
    }

}
