package pl.edu.agh.iet.gg.meshgenerator.productions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public abstract class Production extends Thread {

    private CyclicBarrier cyclicBarrier;

    public Production(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public abstract void applyProduction();

    @Override
    public void run() {
        applyProduction();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
