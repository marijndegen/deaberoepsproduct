package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;
import nl.han.dea.marijn.dtos.subscription.subscriptionslist.ListedSubscriptionResponse;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import nl.han.dea.marijn.services.subscriptions.SubscriptionServiceREST;

import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("abonnementen")
public class SubscriptionsController {

    @Inject
    private SubscriptionService subscriptionsService = new SubscriptionServiceREST();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response abonnementen(@QueryParam("token") String token){
        if(subscriptionsService.isValidUser(token)){
            return giveUserSubscriptionList(token);
        }
        return Response.status(401).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAbonnement(AddMySubscriptionRequest request, @QueryParam("token") String token) {
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }
        subscriptionsService.loadUser(token);
        subscriptionsService.addActiveSubscription(request);
        return giveUserSubscriptionList(token);

    }

    private Response giveUserSubscriptionList(String token){
        ListedSubscriptionResponse listedSubscriptionResponse = new ListedSubscriptionResponse();
        subscriptionsService.loadUser(token);
        listedSubscriptionResponse.addSubscriptions(subscriptionsService.activeSubscriptions());
        listedSubscriptionResponse.setTotalPrice(subscriptionsService.calculateTotalAmount());
        return Response.ok().entity(listedSubscriptionResponse).build();
    }

}
