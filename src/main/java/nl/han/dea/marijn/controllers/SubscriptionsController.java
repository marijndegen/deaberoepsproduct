package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;
import nl.han.dea.marijn.dtos.subscription.subscription.GetSubscriptionResponse;
import nl.han.dea.marijn.dtos.subscription.subscriptionslist.ListedSubscriptionResponse;
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
        return giveUserSubscriptionList(token);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAbonnement(AddMySubscriptionRequest request, @QueryParam("token") String token) {
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }
        subscriptionsService.addActiveSubscription(request);
        return giveUserSubscriptionList(token);

    }

    private Response giveUserSubscriptionList(String token){
        ListedSubscriptionResponse listedSubscriptionResponse = new ListedSubscriptionResponse();
        listedSubscriptionResponse.addSubscriptions(subscriptionsService.activeSubscriptions());
        listedSubscriptionResponse.setTotalPrice(subscriptionsService.calculateTotalAmount());
        return Response.ok().entity(listedSubscriptionResponse).build();
    }
}
