package nl.han.dea.marijn.controllers;
import nl.han.dea.marijn.dtos.Login.LoginRequest;
import nl.han.dea.marijn.dtos.Login.LoginResponse;
import nl.han.dea.marijn.services.login.LoginService;
import nl.han.dea.marijn.services.login.LoginServiceImplementation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

//    @Inject
    private LoginService loginService = new LoginServiceImplementation();

//    @Inject
    private LoginResponse loginResponse = new LoginResponse();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request){
        System.out.println("Trying to login user " + request.getUser() + " with secret " + request.getPassword());
        System.out.println(loginService);
        if(loginService.doLogin(request.getUser(), request.getPassword())){
            loginResponse.setUser(loginService.getUserName());
            loginResponse.setToken(loginService.getToken());
            return Response.ok().entity(loginResponse).build();
        }else{
            return Response.status(403).build();
        }
    }
}