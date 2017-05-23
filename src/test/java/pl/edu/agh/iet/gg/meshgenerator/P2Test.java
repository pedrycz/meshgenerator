package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.model.I;

/**
 * Created by ppedrycz
 */
public class P2Test {

    @Test
    public void productionP2aShouldJoinNodesHorizontally() throws Exception {
        //given
        I i = prepareEmptyGraf();

        //when
        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNW(), i.getNE());

        //then
        assert(i.getNW().getBelow().get().getNE().getSE().equals(i.getNE().getBelow().get().getNW().getSW()));
        assert(i.getNW().getBelow().get().getSE().getNE().equals(i.getNE().getBelow().get().getSW().getNW()));
        assert(i.getNW().getBelow().get().getNE().getE().get().equals(i.getNE().getBelow().get().getNW()));
        assert(i.getNW().getBelow().get().getSE().getE().get().equals(i.getNE().getBelow().get().getSW()));

    }

    @Test
    public void productionP2bShouldJoinNodesVertically() throws Exception {
        //given
        I i = prepareEmptyGraf();

        //when
        i.getNW().applyP1();
        i.getSW().applyP1();
        i.applyP2b(i.getNW(), i.getSW());

        //then
        assert(i.getNW().getBelow().get().getSE().getSW().equals(i.getSW().getBelow().get().getNE().getNW()));
        assert(i.getNW().getBelow().get().getSW().getSE().equals(i.getSW().getBelow().get().getNW().getNE()));
        assert(i.getNW().getBelow().get().getSE().getS().get().equals(i.getSW().getBelow().get().getNE()));
        assert(i.getNW().getBelow().get().getSW().getS().get().equals(i.getSW().getBelow().get().getNW()));
    }

    @Test
    public void productionP2cShouldJoinNodesHorizontally() throws Exception {
        //given
        I i = prepareEmptyGraf();

        //when
        i.getSW().applyP1();
        i.getSE().applyP1();
        i.applyP2c(i.getSW(), i.getSE());

        //then
        assert(i.getSW().getBelow().get().getSE().getNE().equals(i.getSE().getBelow().get().getSW().getNW()));
        assert(i.getSW().getBelow().get().getNE().getSE().equals(i.getSE().getBelow().get().getNW().getSW()));
        assert(i.getSW().getBelow().get().getNE().getE().get().equals(i.getSE().getBelow().get().getNW()));
        assert(i.getSW().getBelow().get().getSE().getE().get().equals(i.getSE().getBelow().get().getSW()));
    }

    @Test
    public void productionP2dShouldJoinNodesVertically() throws Exception {
        //given
        I i = prepareEmptyGraf();

        //when
        i.getNE().applyP1();
        i.getSE().applyP1();
        i.applyP2d(i.getNE(), i.getSE());

        //then
        assert(i.getNE().getBelow().get().getSW().getSE().equals(i.getSE().getBelow().get().getNW().getNE()));
        assert(i.getNE().getBelow().get().getSE().getSW().equals(i.getSE().getBelow().get().getNE().getNW()));
        assert(i.getNE().getBelow().get().getSE().getS().get().equals(i.getSE().getBelow().get().getNE()));
        assert(i.getNE().getBelow().get().getSW().getS().get().equals(i.getSE().getBelow().get().getNW()));

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2aShouldNotWorkOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2a(i.getNW(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2bShouldNotWorkOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2b(i.getNW(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2cShouldNotWorkOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2c(i.getNW(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2dShouldNotWorkOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2d(i.getNW(), i.getSE());

    }

    private I pepareDiagonalWE() {
        I i = prepareEmptyGraf();

        //when
        i.getNW().applyP1();
        i.getSE().applyP1();
        return i;
    }


    @Test(expected = IllegalStateException.class)
    public void productionP2aShouldNotWorkOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2a(i.getNE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2bShouldNotWorkOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2b(i.getNE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2cShouldNotWorkOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2c(i.getNE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2dShouldNotWorkOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2d(i.getNE(), i.getSW());

    }

    private I pepareDiagonalEW() {
        I i = prepareEmptyGraf();

        //when
        i.getNE().applyP1();
        i.getSW().applyP1();
        return i;
    }

    @Test(expected = IllegalStateException.class)
    public void productionP2aShouldNotWorkOnNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2a(i.getNE(), i.getSE());

    }


    @Test(expected = IllegalStateException.class)
    public void productionP2bShouldNotWorkOnNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2b(i.getNE(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2cShouldNotWorkOnNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2c(i.getNE(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2dShouldNotWorkOnNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2d(i.getNE(), i.getSE());

    }

    private I prepareEmptyGraf() {
        Graph graph = Graph.getInstance();
        graph.getRoot().applyP1();
        return graph.getRoot().getBelow().get();
    }

    @Test(expected = IllegalStateException.class)
    public void productionP2aShouldNotWorkInWrongDirection(){
        I i = prepareEmptyGraf();


        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNE(), i.getNW());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2bShouldNotWorkInWrongDirection(){
        I i = prepareEmptyGraf();

        i.getNW().applyP1();
        i.getSW().applyP1();
        i.applyP2b(i.getSW(), i.getNW());


    }

    @Test(expected = IllegalStateException.class)
    public void productionP2cShouldNotWorkInWrongDirection(){
        I i = prepareEmptyGraf();

        i.getSW().applyP1();
        i.getSE().applyP1();
        i.applyP2c(i.getSE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2dShouldNotWorkInWrongDirection(){
        I i = prepareEmptyGraf();

        i.getNE().applyP1();
        i.getSE().applyP1();
        i.applyP2d(i.getSE(), i.getNE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2aShouldNotWorkOnSameNode(){
        I i = prepareEmptyGraf();


        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNE(), i.getNE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2bShouldNotWorkOnSameNode(){
        I i = prepareEmptyGraf();

        i.getNW().applyP1();
        i.getSW().applyP1();
        i.applyP2b(i.getSW(), i.getSW());


    }

    @Test(expected = IllegalStateException.class)
    public void productionP2cShouldNotWorkOnSameNode(){
        I i = prepareEmptyGraf();

        i.getSW().applyP1();
        i.getSE().applyP1();
        i.applyP2c(i.getSE(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void productionP2dShouldNotWorkOnSameNode(){
        I i = prepareEmptyGraf();

        i.getNE().applyP1();
        i.getSE().applyP1();
        i.applyP2d(i.getSE(), i.getSE());

    }



}
