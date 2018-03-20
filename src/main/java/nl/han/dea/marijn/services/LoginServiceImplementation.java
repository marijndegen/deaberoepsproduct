package nl.han.dea.marijn.services;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.User;

public class LoginServiceImplementation implements LoginService {
    public boolean doLogin(String username, String password){
        JDBC.start();
        User user = User.findFirst("user = ?", username);
        JDBC.stop();
        if(user != null)
            return user.get("password").equals(password);
        return false;
    }
}
