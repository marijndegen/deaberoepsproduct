package nl.han.dea.marijn.database.config;

public class Config {

    private String driver = "com.mysql.jdbc.Driver",
            url = "jdbc:mysql://localhost/vodagone",
            user = "vodagone",
            password = "myawesomepasswordyall";

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }


}
