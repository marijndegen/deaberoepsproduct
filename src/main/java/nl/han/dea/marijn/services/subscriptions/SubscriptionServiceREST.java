package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceREST implements SubscriptionService {

    private User user;

    public boolean isValidUser(String token) {
        User user = this.retrieveUser(token);

        return (user instanceof User);
    }

    public void loadUser(String token) {
        user = this.retrieveUser(token);
    }


/*    public List<Subscription> activeSubscriptions() {
        JDBC.start();
        List<ActiveSubscription> activeSubscriptions = ActiveSubscription.where("user_id = ?", user.getId());
        List<Subscription> subscriptions = new ArrayList<>();
        for (ActiveSubscription activeSubscription:
             activeSubscriptions) {
            subscriptions.add(Subscription.findById(activeSubscription.get("subscription_id")));
        }
        JDBC.stop();
        return subscriptions;
    }

    public List<nl.han.dea.marijn.dtos.subscription.Subscription> convertToDataMapper(List<Subscription> subscriptionModels) {
        List<nl.han.dea.marijn.dtos.subscription.Subscription> subscriptionDataTransfer = new ArrayList<>();
        for (Subscription subscription:
                subscriptionModels) {
            subscriptionDataTransfer.add(new nl.han.dea.marijn.dtos.subscription.Subscription(subscription));
        }
        return subscriptionDataTransfer;
    }*/
    public List<nl.han.dea.marijn.dtos.subscription.Subscription> activeSubscriptions(){
        List<nl.han.dea.marijn.dtos.subscription.Subscription> dtosSubscriptions = new ArrayList<>();
        JDBC.start();
        List<Subscription> subscriptions = Subscription.getUserSubscriptions((Integer) this.user.getId());
        for (Subscription subscription: subscriptions) {
            dtosSubscriptions.add(new nl.han.dea.marijn.dtos.subscription.Subscription(subscription)); //Convert the fetched model to a dtos object.
        }
        JDBC.stop();
        return dtosSubscriptions;
    }

    public double calculateTotalAmount() {
        JDBC.start();
        double amount = user.calculateTotalAmount();
        JDBC.stop();
        return amount;
    }

    //todo hier een dto terug geven.
    public nl.han.dea.marijn.dtos.subscription.ActiveSubscription addActiveSubscription(AddMySubscriptionRequest request) {
        JDBC.start();
        Subscription subscription = Subscription.findById(request.getId());
        ActiveSubscription activeSubscription = ActiveSubscription.makeNewStandardActiveSubscription(user, subscription);
        JDBC.stop();
        return new nl.han.dea.marijn.dtos.subscription.ActiveSubscription(activeSubscription);
    }

    public User retrieveUser(String token){
        JDBC.start();
        User user = User.findFirst("token = ? ", token);
        JDBC.stop();
        return user;
    }
}
