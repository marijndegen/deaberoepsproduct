package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("jodenkoek")
public class testcontroller {
    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response abonnementen(){
        JDBC.start();
        List<ActiveSubscription> active = User.where("id = ?", 1);
        System.out.println(active);
        JDBC.stop();
        return Response.ok().entity(active).build();
    }
}
