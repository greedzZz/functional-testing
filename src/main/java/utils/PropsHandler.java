package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsHandler {

    public static final Properties PROPERTIES = new Properties();

    static {
        load();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void load() {
        try {
            PROPERTIES.load(new FileInputStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
