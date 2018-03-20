package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.controllers.LoginController;
import nl.han.dea.marijn.dtos.LoginRequest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class LoginTest {

    @Test
    public void emptyRequestReturnsState403(){
        LoginRequest rq = new LoginRequest();

        LoginController loginEndpoint = new LoginController();

        Response resp = loginEndpoint.login(rq);

        Assert.assertEquals(403, resp.getStatus());


    }

    @Test
    public void emptyRequestReturnsState200(){
        LoginRequest rq = new LoginRequest();
        rq.setUser("meron");
        rq.setPassword("asdfasdf");

        LoginController loginEndpoint = new LoginController();

        Response resp = loginEndpoint.login(rq);

        Assert.assertEquals(200, resp.getStatus());


    }
}