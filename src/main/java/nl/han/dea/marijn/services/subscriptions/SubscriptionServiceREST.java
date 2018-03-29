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
        loadUser(token);
        return (user != null);
    }

    public void loadUser(String token) {
        user = this.retrieveUser(token);
    }

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

    public nl.han.dea.marijn.dtos.subscription.ActiveSubscription addActiveSubscription(AddMySubscriptionRequest request) {
        JDBC.start();
        Subscription subscription = Subscription.findById(request.getId());
        ActiveSubscription activeSubscription = ActiveSubscription.makeNewStandardActiveSubscription(user, subscription);
        JDBC.stop();
        return new nl.han.dea.marijn.dtos.subscription.ActiveSubscription(activeSubscription);
    }

    private User retrieveUser(String token){
        JDBC.start();
        User user = User.findFirst("token = ? ", token);
        JDBC.stop();
        return user;
    }
}
