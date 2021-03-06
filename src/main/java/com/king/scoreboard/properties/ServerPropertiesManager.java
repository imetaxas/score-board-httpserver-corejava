package com.king.scoreboard.properties;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ioannis.metaxas on 2015-11-29.
 *
 * Loads and manages the server properties
 */
public class ServerPropertiesManager {

    private static final Logger LOGGER = Logger.getLogger("confLogger");

    private static final String SERVER_PROPERTIES = "server.properties";

    private static volatile ServerPropertiesManager instance = null;
    private final static Object lock = new Object();

    private final Properties properties;

    private ServerPropertiesManager() {
        properties = new Properties();
        load();
    }

    /**
     * Creates or reuses the manager's instance.
     * Ensures that only a Singleton instance is used.
     *
     * @return the manager's instance.
     */
    public static ServerPropertiesManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new ServerPropertiesManager();
            }
        }
        return instance;
    }

    /**
     * Loads the server properties from a file
     */
    void load() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(SERVER_PROPERTIES));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't load server properties...", e);
        }
    }

    protected String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}
