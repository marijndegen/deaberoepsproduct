package nl.han.dea.marijn.services.login;

import nl.han.dea.marijn.database.models.User;

public interface LoginService {
    boolean doLogin(String username, String password);

    String getUserName();

    String getToken();
}
