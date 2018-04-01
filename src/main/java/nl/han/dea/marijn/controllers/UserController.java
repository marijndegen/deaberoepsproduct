package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.user.request.ShareRequest;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import nl.han.dea.marijn.services.users.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("abonnees")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private  SubscriptionService subscriptionService;


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response allAbonnees(@QueryParam("token") String token){
        if(!subscriptionService.isValidUser(token)){
            return Response.status(401).build();
        }
        return Response.ok().entity(userService.allUsers()).build();
    }

    @Path("{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sharingIsCaring(@QueryParam("token") String token, @PathParam("userId") int userId, ShareRequest shareRequest){
        if(!subscriptionService.isValidUser(token)){
            return Response.status(401).build();
        }
        User leach = subscriptionService.retrieveUser(userId);
        ActiveSubscription activeSubscription = subscriptionService.getIndividualActiveSubscription((Integer) shareRequest.getId());
        userService.addSharedSubscription(activeSubscription, leach);
        return Response.ok().build();
    }
}
