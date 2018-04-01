package nl.han.dea.marijn.services.subscriptions;

import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.subscription.subscription.AddMySubscriptionRequest;
import nl.han.dea.marijn.dtos.subscription.subscription.GetSubscriptionResponse;

import java.util.List;

public interface SubscriptionService {
    boolean isValidUser(String token);

    void loadUser(String token);

    User retrieveUser(String token);

    User retrieveUser(int userId);

    List<nl.han.dea.marijn.dtos.subscription.Subscription> activeSubscriptions();

    double calculateTotalAmount();

    void addActiveSubscription(AddMySubscriptionRequest request);

    ActiveSubscription getIndividualActiveSubscription(int activeSubscriptionId);

    Subscription getIndividualSubscription(int subscriptionId);

    int getSubscriptionIdByActiveSubscription(ActiveSubscription activeSubscription);

    void setSubscriptionInactive(ActiveSubscription activeSubscription);

    void setSubscriptionVerdubbeld(ActiveSubscription activeSubscription, Subscription subscription);

    List<nl.han.dea.marijn.dtos.subscription.Subscription> searchAllSubscriptions(String filter);
}
