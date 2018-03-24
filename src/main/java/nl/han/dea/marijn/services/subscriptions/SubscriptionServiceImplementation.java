package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;
import org.javalite.activejdbc.Base;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceImplementation implements SubscriptionService {

    private User user;

    public boolean isValidUser(String token) {
        User user = this.retrieveUser(token);

        if(user instanceof User)
                return true;
            return false;
    }

    public void loadUser(String token) {
        user = this.retrieveUser(token);
    }


    public List<Subscription> activeSubscriptions() {
        List<ActiveSubscription> activeSubscriptions = ActiveSubscription.where("user_id = ?", user.getId());
        List<Subscription> subscriptions = new ArrayList<>();
        JDBC.start();
        for (ActiveSubscription activeSubscription:
             activeSubscriptions) {
            subscriptions.add(Subscription.findById(activeSubscription.getId()));
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
    }

    public double calculateTotalAmount() {
        JDBC.start();
        String query = "SELECT SUM(subscriptions.price)\n" +
                "FROM users\n" +
                "INNER JOIN activesubscriptions ON users.id = activesubscriptions.user_id\n" +
                "INNER JOIN subscriptions ON activesubscriptions.subscription_id = subscriptions.id\n" +
                "GROUP BY users.id\n" +
                "HAVING users.id = ?";
        Object amount = Base.firstCell(query, this.user.get("id"));
        JDBC.stop();
        return (double) amount;
    }

    public User retrieveUser(String token){
        JDBC.start();
        User user = User.findFirst("token = ? ", token);
        JDBC.stop();
        return user;
    }
}
