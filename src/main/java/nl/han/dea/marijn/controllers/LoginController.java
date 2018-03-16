package nl.han.dea.marijn.controllers;
import nl.han.dea.marijn.dtos.LoginRequest;
import nl.han.dea.marijn.dtos.LoginResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request){
        LoginResponse loginResponse = new LoginResponse();

        if("meron".equals(request.getUser())){
            loginResponse.setUser("meron");
            loginResponse.setToken("jas;lkdfjalksdfj;kla");

            return Response.ok().entity(loginResponse).build();
        }else{
            return Response.status(403).build();
        }
    }
}