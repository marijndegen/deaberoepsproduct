package nl.han.dea.marijn.database.config;

import org.javalite.activejdbc.Base;

public class JDBC {
    public static void start(){
        Config databaseConfig = new Config();
        Base.open(databaseConfig.getDriver(), databaseConfig.getUrl(), databaseConfig.getUser(), databaseConfig.getPassword());
    }

    public static void stop(){
        Base.close();
    }
}
