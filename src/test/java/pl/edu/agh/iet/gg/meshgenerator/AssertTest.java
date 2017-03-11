package pl.edu.agh.iet.gg.meshgenerator;

import org.junit.Test;
import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.util.assertion.Assert;

public class AssertTest {

    @Test
    public void testIsInstanceWithBaseClass() throws Exception {
        Node node = new I();
        Assert.isInstance(node, Node.class);
    }

    @Test
    public void testIsInstanceWithSuitableChildClass() throws Exception {
        Node node = new I();
        Assert.isInstance(node, I.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsInstanceWithWrongChildClass() throws Exception {
        Node node = new I();
        Assert.isInstance(node, E.class);
    }

}
