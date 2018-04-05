package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.dtos.login.LoginRequest;
import nl.han.dea.marijn.services.login.LoginService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void emptyRequestReturnsState403(){
        Mockito.when(loginService.doLogin("","")).thenReturn(false);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser("");
        loginRequest.setPassword("");
        Response resp = loginController.login(loginRequest);
        Mockito.verify(loginService, Mockito.times(1)).doLogin("","");
        Assert.assertEquals(403, resp.getStatus());
    }

    @Test
    public void successLogin200(){
        String username = "bassie";
        String password = "adriaan";

        Mockito.when(loginService.doLogin(username,password)).thenReturn(true);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser(username);
        loginRequest.setPassword(password);
        Response response = loginController.login(loginRequest);
        Mockito.verify(loginService, Mockito.times(1)).doLogin(username,password);
        Assert.assertEquals(200, response.getStatus());
    }
}