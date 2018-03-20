package nl.han.dea.marijn.services.login;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.services.token.TokenGenerator;
import nl.han.dea.marijn.services.token.UUIDGenerator;

import javax.inject.Inject;

public class LoginServiceImplementation implements LoginService {
    private User user;

//    @Inject
    private TokenGenerator generator = new UUIDGenerator();

    public boolean doLogin(String username, String password){
        JDBC.start();
        User user = User.findFirst("user = ?", username);
        JDBC.stop();
        if(user != null){
            this.user = user;
            generateAndSetToken();
            return user.get("password").equals(password);
        }
        return false;
    }

    private void generateAndSetToken(){
        JDBC.start();
        String token = generator.generateToken();
        user.set("token", token);
        user.save();
        JDBC.stop();
    }

    public String getUserName() {
        return user.get("user").toString();
    }

    public String getToken(){
        return user.get("token").toString();
    }
}
