package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;

import java.util.List;

public interface SubscriptionService {
    User retrieveUser(String token);

    boolean isValidUser(String token);

    void loadUser(String token);

    List<nl.han.dea.marijn.dtos.subscription.Subscription> activeSubscriptions();

//    List<nl.han.dea.marijn.dtos.subscription.Subscription> convertToDataMapper(List<Subscription> subscriptions);

    double calculateTotalAmount();

    nl.han.dea.marijn.dtos.subscription.ActiveSubscription addActiveSubscription(AddMySubscriptionRequest request);
}
