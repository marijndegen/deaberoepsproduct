package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;


@RunWith(MockitoJUnitRunner.class)
public class SubscriptionTest {
    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private SubscriptionController subscriptionController;

    @Test
    public void testGetSingleAbonnement200(){
        int userId = 2;
        int activeSubscriptionId = 34;
        JDBC.start();
        User user = User.findById(userId);
        String token = (String) user.get("token");
        ActiveSubscription activeSubscription   = ActiveSubscription.findById(activeSubscriptionId);
        int subscriptionId                      = (Integer) activeSubscription.get("subscription_id");
        Subscription subscription               = Subscription.findById(subscriptionId);
        JDBC.stop();


        Mockito.when(subscriptionService.isValidUser(token)).thenReturn(true);
        Mockito.when(subscriptionService.getIndividualActiveSubscription(activeSubscriptionId)).thenReturn(activeSubscription);
        Mockito.when(subscriptionService.getSubscriptionIdByActiveSubscription(activeSubscription)).thenReturn(subscriptionId);
        Mockito.when(subscriptionService.getIndividualSubscription(subscriptionId)).thenReturn(subscription);

        Response response = subscriptionController.getAbonnement(token, activeSubscriptionId);

        Mockito.verify(subscriptionService, Mockito.times(1)).isValidUser(token);
        Mockito.verify(subscriptionService, Mockito.times(1)).getIndividualActiveSubscription(activeSubscriptionId);
        Mockito.verify(subscriptionService, Mockito.times(1)).getSubscriptionIdByActiveSubscription(activeSubscription);
        Mockito.verify(subscriptionService, Mockito.times(1)).getIndividualSubscription(subscriptionId);

        Assert.assertEquals(200, response.getStatus());
    }
    //todo eventueel nog meer testen bedenken.
}
