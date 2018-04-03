package nl.han.dea.marijn.controllers;
import nl.han.dea.marijn.dtos.login.LoginRequest;
import nl.han.dea.marijn.dtos.login.LoginResponse;
import nl.han.dea.marijn.services.login.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @Inject
    private LoginResponse loginResponse;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request){
        if(loginService.doLogin(request.getUser(), request.getPassword())){
            loginResponse.setUser(loginService.getUserName());
            loginResponse.setToken(loginService.getToken());
            return Response.ok().entity(loginResponse).build();
        }else{
            return Response.status(403).build();
        }
    }
}