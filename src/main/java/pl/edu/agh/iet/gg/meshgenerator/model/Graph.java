package pl.edu.agh.iet.gg.meshgenerator.model;

/**
 * @author Wojciech Pachuta.
 */
public class Graph {
    private final E root;

    public Graph() {
        this.root = new E();
    }

    public E getRoot() {
        return root;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.getRoot().applyP1();

        I i = g.getRoot().getBelow().get();

        E nw = i.getNw();
        E sw = i.getSw();
        E se = i.getSe();
        E ne = i.getNe();

        nw.applyP1();
        sw.applyP1();
        se.applyP1();
        ne.applyP1();

        i.applyP2a(nw, ne);
        i.applyP2b(nw, sw);
        i.applyP2c(sw, se);
        i.applyP2d(ne, se);

        System.out.println(g);

    }
}
