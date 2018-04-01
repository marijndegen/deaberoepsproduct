package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.enums.Status;
import nl.han.dea.marijn.database.enums.Verdubbeling;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;

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
        JDBC.start();
        List<nl.han.dea.marijn.dtos.subscription.Subscription> subscriptions = ActiveSubscription.getActiveSubscriptions((Integer) user.getId());
        JDBC.stop();
        return subscriptions;
    }

    public double calculateTotalAmount() {
        JDBC.start();
        System.out.println(user);
        double amount = user.calculateTotalAmount();
        JDBC.stop();
        return amount;
    }

    public void addActiveSubscription(AddMySubscriptionRequest request) {
        JDBC.start();
        Subscription subscription = Subscription.findById(request.getId());
        ActiveSubscription.makeNewStandardActiveSubscription(user, subscription);
        JDBC.stop();
    }

    public ActiveSubscription getIndividualActiveSubscription(int activeSubscriptionId) {
        JDBC.start();
        ActiveSubscription activeSubscription = ActiveSubscription.findById(activeSubscriptionId);
        JDBC.stop();
        return activeSubscription;
    }

    public int getSubscriptionIdByActiveSubscription(ActiveSubscription activeSubscription) {
        JDBC.start();
        int subscriptionId = (Integer) activeSubscription.get("subscription_id");
        JDBC.stop();
        return subscriptionId;
    }

    public void setSubscriptionInactive(ActiveSubscription activeSubscription) {
        JDBC.start();
        activeSubscription.set("status", Status.OPGEZEGD.getEnumValue());
        activeSubscription.save();
        JDBC.stop();
    }

    public void setSubscriptionVerdubbeld(ActiveSubscription activeSubscription, Subscription subscription) {
        boolean dubbelAble = (Boolean) subscription.get("dubbelable");
        if(dubbelAble){
            JDBC.start();
            activeSubscription.set("dubbel", Verdubbeling.VERDUBBELD.getEnumValue());
            activeSubscription.save();
            JDBC.stop();
        }
    }

    public List<nl.han.dea.marijn.dtos.subscription.Subscription> searchAllSubscriptions(String filter) {
        JDBC.start();
        List<nl.han.dea.marijn.dtos.subscription.Subscription> subscriptions = Subscription.searchSubscription(filter);
        JDBC.stop();
        return subscriptions;
    }

    public Subscription getIndividualSubscription(int subscriptionId) {
        JDBC.start();
        Subscription subscription = Subscription.findById(subscriptionId);
        JDBC.stop();
        return subscription;
    }

    public User retrieveUser(String token){
        JDBC.start();
        User user = User.findFirst("token = ? ", token);
        JDBC.stop();
        return user;
    }

    @Override
    public User retrieveUser(int userId) {
        JDBC.start();
        User user = User.findById(userId);
        JDBC.stop();
        return user;
    }
}
