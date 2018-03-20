package nl.han.dea.marijn.controllers;
import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.LoginRequest;
import nl.han.dea.marijn.dtos.LoginResponse;
import nl.han.dea.marijn.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request){
        LoginResponse loginResponse = new LoginResponse();

        if(loginService.doLogin(request.getUser(), request.getPassword())){
            loginResponse.setUser("meron");
            loginResponse.setToken("jas;lkdfjalksdfj;kla");

            return Response.ok().entity(loginResponse).build();
        }else{
            return Response.status(403).build();
        }
    }
}