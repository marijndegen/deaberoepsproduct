package nl.han.dea.marijn.database.config;

import org.javalite.activejdbc.Base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBC {
    public static void start(){
        Properties props = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("resources.properties");
        try {
            props.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver   = props.getProperty("jdbc.driver");
        String url      = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
/*        System.out.println(driver);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);*/
        Base.open(driver, url, username, password);
    }

    public static void stop(){
        Base.close();
    }
}
