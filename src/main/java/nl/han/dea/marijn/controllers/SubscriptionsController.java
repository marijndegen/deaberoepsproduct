package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;
import nl.han.dea.marijn.dtos.subscription.subscriptionslist.ListedSubscriptionResponse;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import nl.han.dea.marijn.services.subscriptions.SubscriptionServiceREST;

import javax.json.JsonObject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("abonnementen")
public class SubscriptionsController {

//    @Inject TODO
    private SubscriptionService subscriptionsService = new SubscriptionServiceREST();

//    @Inject TODO

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
//        List<Subscription> activeSubscriptions = subscriptionsService.activeSubscriptions();
//        listedSubscriptionResponse.addSubscriptions(subscriptionsService.convertToDataMapper(activeSubscriptions));
        listedSubscriptionResponse.addSubscriptions(subscriptionsService.activeSubscriptions());
        listedSubscriptionResponse.setTotalPrice(subscriptionsService.calculateTotalAmount());
//        this.testFunctiontestFunction();
        return Response.ok().entity(listedSubscriptionResponse).build();
    }

/*    private void testFunctiontestFunction(){
        JDBC.start();
        String sql = "SELECT subscriptions.* \n" +
                "FROM activesubscriptions \n" +
                "INNER JOIN subscriptions ON activesubscriptions.subscription_id = subscriptions.id \n" +
                "WHERE activesubscriptions.user_id = 1";
        Object result = Subscription.findBySQL(sql);

        for (Subscription subscription: (List<Subscription>) result
             ) {
            System.out.println(subscription.get("service"));
        }

        System.out.println(result);
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        Subscription.findAll();
        JDBC.stop();

    }*/

}
