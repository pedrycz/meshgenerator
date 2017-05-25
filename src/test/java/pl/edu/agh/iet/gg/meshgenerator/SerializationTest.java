package pl.edu.agh.iet.gg.meshgenerator;
import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.Graph;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.visualization.controller.MenuController;


import java.io.*;
import java.util.Base64;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kamil on 2017-05-24.
 */
public class SerializationTest {

    @Test
    public void checkingGraphStructureNEOneLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        assertNotNull(deserializedGraph.getRoot().getBelow().get().getNE());
    }

    @Test
    public void checkingGraphStructureNWOneLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        assertNotNull(deserializedGraph.getRoot().getBelow().get().getNW());
    }

    @Test
    public void checkingGraphStructureSEOneLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        assertNotNull(deserializedGraph.getRoot().getBelow().get().getSE());
    }

    @Test
    public void checkingGraphStructureSWOneLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        assertNotNull(deserializedGraph.getRoot().getBelow().get().getSW());
    }

    @Test
    public void checkingGraphStructureNETwoLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        assertNotNull(deserializedGraph.getRoot().getBelow().get().getNE().getBelow().get());
    }

    @Test
    public void checkingGraphStructureNWTwoLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        assertNotNull(deserializedGraph.getRoot().getBelow().get().getNW().getBelow().get());
    }

    @Test (expected = NoSuchElementException.class)
    public void checkingGraphStructureSETwoLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        I i = deserializedGraph.getRoot().getBelow().get().getSE().getBelow().get();
    }

    @Test (expected = NoSuchElementException.class)
    public void checkingGraphStructureSWTwoLevelBelow(){
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        I i = deserializedGraph.getRoot().getBelow().get().getSW().getBelow().get();
    }

    @Test (expected = NoSuchElementException.class)
    public void checkingGraphStructureNWThreeLevelBelow() throws Exception {
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        I i = deserializedGraph.getRoot().getBelow().get().getNW().getBelow().get().getNW().getBelow().get();
    }

    @Test (expected = NoSuchElementException.class)
    public void checkingGraphStructureSWThreeLevelBelow() throws Exception {
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        I i = deserializedGraph.getRoot().getBelow().get().getNW().getBelow().get().getSW().getBelow().get();
    }

    @Test (expected = NoSuchElementException.class)
    public void checkingGraphStructureSEThreeLevelBelow() throws Exception {
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        I i = deserializedGraph.getRoot().getBelow().get().getNW().getBelow().get().getSE().getBelow().get();
    }

    @Test (expected = NoSuchElementException.class)
    public void checkingGraphStructureNEThreeLevelBelow() throws Exception {
        Graph deserializedGraph = getDeserializedGraph(prepareGraph());
        I i = deserializedGraph.getRoot().getBelow().get().getNW().getBelow().get().getNE().getBelow().get();
    }

    public Graph prepareGraph(){
        Graph graph = Graph.getInstance();
        graph.getRoot().applyP1();
        I i = graph.getRoot().getBelow().get();
        i.getNW().applyP1();
        i.getNE().applyP1();
        i.applyP2a(i.getNW(), i.getNE());
        return graph;
    }

    public Graph getDeserializedGraph(Graph graph){
        try {
            String serializedGraph = toString(graph);
            return ( Graph ) fromString( serializedGraph );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Write the object to a Base64 string
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    //Read the object from Base64 string
    private static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

}
