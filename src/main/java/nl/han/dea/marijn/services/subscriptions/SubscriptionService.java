package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;

import java.util.List;

public interface SubscriptionService {
    boolean isValidUser(String token);

    void loadUser(String token);

    List<Subscription> subscriptions();

    User retrieveUser(String token);




}
