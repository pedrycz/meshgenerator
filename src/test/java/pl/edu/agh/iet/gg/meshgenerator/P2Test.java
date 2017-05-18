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
    public void cantMakeP2aOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2a(i.getNW(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2bOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2b(i.getNW(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2cOverDiagonalWE(){
        I i = pepareDiagonalWE();

        i.applyP2c(i.getNW(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2dOverDiagonalWE(){
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
    public void cantMakeP2aOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2a(i.getNE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2bOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2b(i.getNE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2cOverDiagonalEW(){
        I i = pepareDiagonalEW();

        i.applyP2c(i.getNE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2dOverDiagonalEW(){
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
    public void cantMakeP2aOverNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2a(i.getNE(), i.getSE());

    }


    @Test(expected = IllegalStateException.class)
    public void cantMakeP2bOverNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2b(i.getNE(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2cOverNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2c(i.getNE(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2dOverNonExistingProductions(){
        I i = prepareEmptyGraf();
        i.applyP2d(i.getNE(), i.getSE());

    }

    private I prepareEmptyGraf() {
        Graph graph = new Graph();
        graph.getRoot().applyP1();
        return graph.getRoot().getBelow().get();
    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2aInWrongDirection(){
        I i = prepareEmptyGraf();


        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNE(), i.getNW());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2bInWrongDirection(){
        I i = prepareEmptyGraf();

        i.getNW().applyP1();
        i.getSW().applyP1();
        i.applyP2b(i.getSW(), i.getNW());


    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2cInWrongDirection(){
        I i = prepareEmptyGraf();

        i.getSW().applyP1();
        i.getSE().applyP1();
        i.applyP2c(i.getSE(), i.getSW());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2dInWrongDirection(){
        I i = prepareEmptyGraf();

        i.getNE().applyP1();
        i.getSE().applyP1();
        i.applyP2d(i.getSE(), i.getNE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2aWithSameNode(){
        I i = prepareEmptyGraf();


        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNE(), i.getNE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2bWithSameNode(){
        I i = prepareEmptyGraf();

        i.getNW().applyP1();
        i.getSW().applyP1();
        i.applyP2b(i.getSW(), i.getSW());


    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2cWithSameNode(){
        I i = prepareEmptyGraf();

        i.getSW().applyP1();
        i.getSE().applyP1();
        i.applyP2c(i.getSE(), i.getSE());

    }

    @Test(expected = IllegalStateException.class)
    public void cantMakeP2dWithSameNode(){
        I i = prepareEmptyGraf();

        i.getNE().applyP1();
        i.getSE().applyP1();
        i.applyP2d(i.getSE(), i.getSE());

    }



}
