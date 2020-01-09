package com.epam.util;

import java.io.*;
import java.util.Properties;

public class AppConfig {

    public static String getHost() throws IOException {
        File file = new File("./src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties.getProperty("db.host");
    }

    public static String getLogin() throws IOException {
        File file = new File("./src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties.getProperty("db.login");

    }

    public static String getPass() throws IOException {

        File file = new File("./src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties.getProperty("db.password");
    }
}
