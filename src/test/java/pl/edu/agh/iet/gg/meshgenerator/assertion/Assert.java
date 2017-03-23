package pl.edu.agh.iet.gg.meshgenerator.assertion;

public class Assert {

    private Assert() {
    }


    public static void isInstance(Object instance, Class<?> cls) throws IllegalArgumentException {
        if (!cls.isInstance(instance)) {
            throw new IllegalArgumentException("Object " + instance + " is not an instance of the class "
                    + cls.getName() + ".");
        }
    }

    public static void isIndexInBounds(int arrayIndex, int arrayLength) {
        if (!(arrayIndex >= 0 && arrayIndex < arrayLength)) {
            throw new IllegalArgumentException("Index " + arrayIndex + " cannot be an index of an array of length "
                    + arrayLength + ".");
        }
    }

}
