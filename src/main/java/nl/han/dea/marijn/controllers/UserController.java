package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.dtos.subscription.subscription.GetSubscriptionResponse;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/abonnementen")
public class UserController {

    @Inject
    private SubscriptionService subscriptionService;

    @GET
    @Path("{subscriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAbonnement(@QueryParam("token") String token, @PathParam("subscriptionId") int activeSubscriptionId){
        if(!subscriptionService.isValidUser(token)){
            return Response.status(401).build();
        }

        ActiveSubscription activeSubscription = subscriptionService.getIndividualActiveSubscription(activeSubscriptionId);
        int subscriptionId = subscriptionService.getSubscriptionIdByActiveSubscription(activeSubscription);
        Subscription subscription = subscriptionService.getIndividualSubscription(subscriptionId);
        return individualActiveSubscription(activeSubscription, subscription);
    }

    @DELETE
    @Path("{subscriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setActiveSubscriptionInactive(@QueryParam("token") String token, @PathParam("subscriptionId") int activeSubscriptionId){
        if(!subscriptionService.isValidUser(token)){
            return Response.status(401).build();
        }
        ActiveSubscription activeSubscription = subscriptionService.getIndividualActiveSubscription(activeSubscriptionId);
        int subscriptionId = subscriptionService.getSubscriptionIdByActiveSubscription(activeSubscription);
        Subscription subscription = subscriptionService.getIndividualSubscription(subscriptionId);
        subscriptionService.setSubscriptionInactive(activeSubscription);
        return individualActiveSubscription(activeSubscription, subscription);
    }

    @POST
    @Path("{subscriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doublePrice(@QueryParam("token") String token, @PathParam("subscriptionId") int activeSubscriptionId){
        if(!subscriptionService.isValidUser(token)){
            return Response.status(401).build();
        }

        ActiveSubscription activeSubscription = subscriptionService.getIndividualActiveSubscription(activeSubscriptionId);
        int subscriptionId = subscriptionService.getSubscriptionIdByActiveSubscription(activeSubscription);
        Subscription subscription = subscriptionService.getIndividualSubscription(subscriptionId);
        subscriptionService.setSubscriptionVerdubbeld(activeSubscription, subscription);
        return individualActiveSubscription(activeSubscription, subscription);
    }

    private Response individualActiveSubscription(ActiveSubscription activeSubscription, Subscription subscription){
        GetSubscriptionResponse getSubscriptionResponse = new GetSubscriptionResponse();
        getSubscriptionResponse.addActiveSubscription(activeSubscription);
        getSubscriptionResponse.addSubscription(subscription);
        return Response.ok().entity(getSubscriptionResponse).build();

    }


}
