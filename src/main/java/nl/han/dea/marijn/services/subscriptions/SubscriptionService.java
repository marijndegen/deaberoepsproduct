package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;

import java.util.List;

public interface SubscriptionService {
    boolean isValidUser(String token);

    void loadUser(String token);

    List<nl.han.dea.marijn.dtos.subscription.Subscription> activeSubscriptions();

    double calculateTotalAmount();

    nl.han.dea.marijn.dtos.subscription.ActiveSubscription addActiveSubscription(AddMySubscriptionRequest request);
}
