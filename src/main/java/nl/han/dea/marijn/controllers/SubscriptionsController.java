package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;
import nl.han.dea.marijn.dtos.subscription.subscriptionslist.MyListedSubscriptionResponse;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import nl.han.dea.marijn.services.subscriptions.SubscriptionServiceImplementation;

import javax.ws.rs.*;

import javax.ws.rs.core.Response;
import java.util.List;

@Path("abonnementen")
public class SubscriptionsController {

//    @Inject
    private SubscriptionService subscriptionsService = new SubscriptionServiceImplementation();

//    @Inject
    private MyListedSubscriptionResponse myListedSubscriptionResponse = new MyListedSubscriptionResponse();

    @GET
    public Response abonnementen(@QueryParam("token") String token){
        if(subscriptionsService.isValidUser(token)){
            return giveUserList(token);
        }
        return Response.status(401).build();
    }

    @POST
    @Path("{id}")
    public Response addAbonnement(AddMySubscriptionRequest request, @PathParam("id") String id, @QueryParam("token") String token) {
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }
        Subscription subscription = Subscription.findById(request.getId());
        if(!(subscription instanceof Subscription)){
            return Response.status(404).build();
        }

        ActiveSubscription.makeNewStandardActiveSubscription(subscriptionsService.retrieveUser(token), subscription);
        return giveUserList(token);

    }

    private Response giveUserList(String token){
        double totalAmount = 0.0;
        subscriptionsService.loadUser(token);
        List<Subscription> subscriptions = subscriptionsService.subscriptions();
        JDBC.start();
        for (Subscription subscription:
                subscriptions) {
            totalAmount += (double) subscription.get("price");
            myListedSubscriptionResponse.addSubscription(new nl.han.dea.marijn.dtos.subscription.Subscription(subscription));
        }
        myListedSubscriptionResponse.setTotalPrice(totalAmount);
        JDBC.stop();
        return Response.ok().entity(myListedSubscriptionResponse).build();
    }

}
