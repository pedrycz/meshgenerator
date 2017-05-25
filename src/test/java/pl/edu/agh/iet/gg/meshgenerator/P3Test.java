package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.model.I;

/**
 * Created by mowczarek on 25.05.17.
 */
public class P3Test {

    @Test
    public void productionP3ShouldJoinNodesDiagonally() throws Exception {
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        i.applyP2a(i.getNW(), i.getNE());
        i.applyP2b(i.getNW(), i.getSW());
        i.applyP2c(i.getSW(), i.getSE());
        i.applyP2d(i.getNE(), i.getSE());

        makeP3Production(i);

        assert(i.getNW().getBelow().get().getSE().getSE().equals(i.getSE().getBelow().get().getNW().getNW()));
        assert(i.getSW().getBelow().get().getNE().getNE().equals(i.getNE().getBelow().get().getSW().getSW()));

    }

    @Test(expected = IllegalStateException.class)
    public void productionP3ShouldNotWorkOnNonExistingP2Productions(){
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        makeP3Production(i);
    }

    @Test(expected = IllegalStateException.class)
    public void productionP3ShouldNotWorkOnMissingP2aProduction(){
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        // missing i.applyP2a(i.getNW(), i.getNE());
        i.applyP2b(i.getNW(), i.getSW());
        i.applyP2c(i.getSW(), i.getSE());
        i.applyP2d(i.getNE(), i.getSE());

        makeP3Production(i);
    }

    @Test(expected = IllegalStateException.class)
    public void productionP3ShouldNotWorkOnMissingP2bProduction(){
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        i.applyP2a(i.getNW(), i.getNE());
        // missing i.applyP2b(i.getNW(), i.getSW());
        i.applyP2c(i.getSW(), i.getSE());
        i.applyP2d(i.getNE(), i.getSE());

        makeP3Production(i);
    }

    @Test(expected = IllegalStateException.class)
    public void productionP3ShouldNotWorkOnMissingP2cProduction(){
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        i.applyP2a(i.getNW(), i.getNE());
        i.applyP2b(i.getNW(), i.getSW());
        // missing i.applyP2c(i.getSW(), i.getSE());
        i.applyP2d(i.getNE(), i.getSE());

        makeP3Production(i);
    }

    @Test(expected = IllegalStateException.class)
    public void productionP3ShouldNotWorkOnMissingP2dProduction(){
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        i.applyP2a(i.getNW(), i.getNE());
        i.applyP2b(i.getNW(), i.getSW());
        i.applyP2c(i.getSW(), i.getSE());
        // missing i.applyP2d(i.getNE(), i.getSE());

        makeP3Production(i);
    }

    @Test(expected = IllegalStateException.class)
    public void productionP3ShouldNotWorkOnTheSameNodes(){
        I emptyI = prepareEmptyGraf();
        I i = makeFourP1Productions(emptyI);

        i.applyP2a(i.getNW(), i.getNE());
        i.applyP2b(i.getNW(), i.getSW());
        i.applyP2c(i.getSW(), i.getSE());
        i.applyP2d(i.getNE(), i.getSE());

        E e1 = i.getSW().getBelow().get().getNE();

        E.applyP3(e1, e1, e1, e1);
    }

    private I prepareEmptyGraf() {
        Graph graph = Graph.getInstance();
        graph.getRoot().applyP1();

        return graph.getRoot().getBelow().get();
    }

    private I makeFourP1Productions(I i) {
        i.getNW().applyP1();
        i.getNE().applyP1();
        i.getSE().applyP1();
        i.getSW().applyP1();

        return i;
    }

    private void makeP3Production(I i) {
        E e1 = i.getSW().getBelow().get().getNE();
        E e2 = i.getNW().getBelow().get().getSE();
        E e3 = i.getNE().getBelow().get().getSW();
        E e4 = i.getSE().getBelow().get().getNW();

        E.applyP3(e1, e2, e3, e4);
    }

}
