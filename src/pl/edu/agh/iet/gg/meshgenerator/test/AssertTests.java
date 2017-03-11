package pl.edu.agh.iet.gg.meshgenerator.test;

import pl.edu.agh.iet.gg.meshgenerator.model.E;
import pl.edu.agh.iet.gg.meshgenerator.model.I;
import pl.edu.agh.iet.gg.meshgenerator.model.Node;
import pl.edu.agh.iet.gg.meshgenerator.util.assertion.Assert;

// TODO: After maven structure creation, add Junit 4.x as a dependency and change this class to a test one.
public class AssertTests {

    public static void main(String[] args) {
        AssertTests testCase = new AssertTests();
        testCase.testIsInstanceWithBaseClass();
        testCase.testIsInstanceWithSuitableChildClass();
        testCase.testIsInstanceWithWrongChildClass();
    }


    private void testIsInstanceWithBaseClass() {
        Node node = new I();
        Assert.isInstance(node, Node.class);
    }

    private void testIsInstanceWithSuitableChildClass() {
        Node node = new I();
        Assert.isInstance(node, I.class);
    }

    private void testIsInstanceWithWrongChildClass() {
        Node node = new I();
        try {
            Assert.isInstance(node, E.class);
        } catch (IllegalArgumentException exc) {
        }
    }

}
