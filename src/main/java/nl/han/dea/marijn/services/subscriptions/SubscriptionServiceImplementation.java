package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceImplementation implements SubscriptionService {

    private User user;

    @Override
    public boolean isValidUser(String token) {
        User user = this.retrieveUser(token);

        if(user instanceof User)
                return true;
            return false;
    }

    public void loadUser(String token) {
        user = this.retrieveUser(token);
    }


    public List<Subscription> subscriptions() {
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

    public User retrieveUser(String token){
        JDBC.start();
        User user = User.findFirst("token = ? ", token);
        JDBC.stop();
        return user;
    }
}
