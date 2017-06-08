package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.ProductionResults;

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

    abstract void applyProduction();

    abstract ProductionResults getProductionResults();

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
