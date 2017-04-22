package pl.edu.agh.iet.gg.meshgenerator.visualization.config;

import pl.edu.agh.iet.gg.meshgenerator.visualization.GraphVisualizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Bartłomiej Grochal
 */
public class Config {

    private static Properties properties;


    private Config() {
    }


    public static String getString(String key) {
        Properties properties = getProperties();
        return properties.getProperty(key);
    }

    public static int getInteger(String key) {
        Properties properties = getProperties();
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException exc) {
            return 0;
        }
    }

    public static float getFloat(String key) {
        Properties properties = getProperties();
        try {
            return Float.parseFloat(properties.getProperty(key));
        } catch (NumberFormatException exc) {
            return 0.0f;
        }
    }

    public static double getDouble(String key) {
        Properties properties = getProperties();
        try {
            return Double.parseDouble(properties.getProperty(key));
        } catch (NumberFormatException exc) {
            return 0.0;
        }
    }


    private static Properties getProperties() {
        if (properties == null) {
            openPropertiesFile();
        }
        return properties;
    }

    private static void openPropertiesFile() {
        properties = new Properties();
        try (InputStream inputStream =
                     new FileInputStream(GraphVisualizer.class.getResource("config/visualizer.properties").getPath())) {
            properties.load(inputStream);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
