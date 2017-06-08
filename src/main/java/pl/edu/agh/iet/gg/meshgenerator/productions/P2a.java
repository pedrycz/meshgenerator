package pl.edu.agh.iet.gg.meshgenerator.productions;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.ProductionResults;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by ppedrycz
 */
public class P2a extends Production {

    private I i;
    private E nw;
    private E ne;
    private ProductionResults productionResults;

    public P2a(CyclicBarrier cyclicBarrier, I i, E nw, E ne) {
        super(cyclicBarrier);
        this.i = i;
        this.nw = nw;
        this.ne = ne;
    }

    @Override
    public void applyProduction() {
        this.productionResults = i.applyP2a(nw, ne);
    }

    @Override
    ProductionResults getProductionResults() {
        return productionResults;
    }
}
