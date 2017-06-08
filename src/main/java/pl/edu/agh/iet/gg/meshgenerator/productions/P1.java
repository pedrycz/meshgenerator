package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.ProductionResults;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public class P1 extends Production {

    private E e;
    private ProductionResults productionResults;

    public P1(CyclicBarrier cyclicBarrier, E e) {
        super(cyclicBarrier);
        this.e = e;
    }

    @Override
    public void applyProduction() {
        this.productionResults = e.applyP1();
    }

    @Override
    ProductionResults getProductionResults() {
        return productionResults;
    }

}
