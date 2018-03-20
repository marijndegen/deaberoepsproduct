package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.Subscription;
//import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.Subscriptions.SubscriptionResponse;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("abonnementen")
public class SubscriptionsController {

    @Inject
    private SubscriptionService subscriptionsService;

    @Inject
    private SubscriptionResponse subscriptionResponse;

    @GET
    public Response abonnementen(@QueryParam("token") String token){
        if(subscriptionsService.isValidUser(token)){
            System.out.println("jaman");
            double totalAmount = 0.0;
            subscriptionsService.loadUser(token);
            List<Subscription> subscriptions = subscriptionsService.subscriptions();
            JDBC.start();
            for (Subscription subscription:
                 subscriptions) {
                totalAmount += (double) subscription.get("price");
                subscriptionResponse.addSubscription(new nl.han.dea.marijn.dtos.Subscriptions.Subscription(subscription));
            }
            JDBC.stop();
            subscriptionResponse.setTotalPrice(totalAmount);
            return Response.ok().entity(subscriptionResponse).build();
        }
        return Response.status(404).build();
    }

}
