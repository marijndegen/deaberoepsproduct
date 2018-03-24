package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.dtos.subscription.subscriptionslist.ListedSubscriptionResponse;
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
    private ListedSubscriptionResponse listedSubscriptionResponse = new ListedSubscriptionResponse();

    @GET
    public Response abonnementen(@QueryParam("token") String token){
        if(subscriptionsService.isValidUser(token)){
            return giveUserSubscriptionList(token);
        }
        return Response.status(401).build();
    }

    /*@POST
    public Response addAbonnement(AddMySubscriptionRequest request, @QueryParam("token") String token) {
        if(!subscriptionsService.isValidUser(token)){
            return Response.status(401).build();
        }
        JDBC.start();
        Subscription subscription = Subscription.findById(request.getId());

        if(!(subscription instanceof Subscription)){
            return Response.status(404).build();
        }

        ActiveSubscription.makeNewStandardActiveSubscription(subscriptionsService.retrieveUser(token), subscription); //TODO opend een nieuwe jdbc thread. Eingelijk is het best practice om jdbc niet te starten in de controller, maar door dit in de service laag te verwerken.
        JDBC.stop();
        return giveUserSubscriptionList(token);

    }*/

    private Response giveUserSubscriptionList(String token){
        subscriptionsService.loadUser(token);
        List<Subscription> activeSubscriptions = subscriptionsService.activeSubscriptions();
/*        JDBC.start();
        for (Subscription subscription:
                activeSubscriptions) {
            totalAmount += (double) subscription.get("price");
            listedSubscriptionResponse.addSubscription(new nl.han.dea.marijn.dtos.subscription.Subscription(subscription));
        }
        listedSubscriptionResponse.setTotalPrice(totalAmount);
        JDBC.stop();*/
        listedSubscriptionResponse.addSubscriptions(subscriptionsService.convertToDataMapper(activeSubscriptions));
        listedSubscriptionResponse.setTotalPrice(subscriptionsService.calculateTotalAmount());
        return Response.ok().entity(listedSubscriptionResponse).build();
    }

}
