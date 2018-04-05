package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.dtos.subscription.Subscription;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionsTest {
    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private SubscriptionsController subscriptionsController;

    @Test
    public void testGetAllAbonnement(){
        int userId = 2;
        JDBC.start();
        User user = User.findById(userId);
        String token = (String) user.get("token");
        List<Subscription> activeSubscriptions = ActiveSubscription.getActiveSubscriptions(userId);
        double totalAmount = user.calculateTotalAmount();
        JDBC.stop();


        Mockito.when(subscriptionService.isValidUser(token)).thenReturn(true);
        Mockito.when(subscriptionService.activeSubscriptions()).thenReturn(activeSubscriptions);
        Mockito.when(subscriptionService.calculateTotalAmount()).thenReturn(totalAmount);

        Response response = subscriptionsController.abonnementen(token);

        Mockito.verify(subscriptionService, Mockito.times(1)).isValidUser(token);
        Mockito.verify(subscriptionService, Mockito.times(1)).activeSubscriptions();
        Mockito.verify(subscriptionService, Mockito.times(1)).calculateTotalAmount();

        Assert.assertEquals(200, response.getStatus());

    }
}
