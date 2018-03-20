package nl.han.dea.marijn.services.login;

public interface LoginService {
    boolean doLogin(String username, String password);

    String getUserName();

    String getToken();
}
