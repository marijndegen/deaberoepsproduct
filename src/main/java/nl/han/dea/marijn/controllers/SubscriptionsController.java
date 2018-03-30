package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;
import nl.han.dea.marijn.dtos.subscription.subscriptionslist.ListedActiveSubscriptionResponse;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;

import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("abonnementen")
public class SubscriptionsController {

    @Inject
    private SubscriptionService subscriptionsService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response abonnementen(@QueryParam("token") String token){
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }
        return giveUserSubscriptionList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAbonnement(AddMySubscriptionRequest request, @QueryParam("token") String token) {
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }
        subscriptionsService.addActiveSubscription(request);
        return giveUserSubscriptionList();
    }

    @Path("all")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAllSubscriptions(@QueryParam("token") String token, @QueryParam("filter") String filter){
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }

        return Response.ok().entity(subscriptionsService.searchAllSubscriptions(filter)).build();
    }

    private Response giveUserSubscriptionList(){
        ListedActiveSubscriptionResponse listedActiveSubscriptionResponse = new ListedActiveSubscriptionResponse();
        listedActiveSubscriptionResponse.addSubscriptions(subscriptionsService.activeSubscriptions());
        listedActiveSubscriptionResponse.setTotalPrice(subscriptionsService.calculateTotalAmount());
        return Response.ok().entity(listedActiveSubscriptionResponse).build();
    }
}
