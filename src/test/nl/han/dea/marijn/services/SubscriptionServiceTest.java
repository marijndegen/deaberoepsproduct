package nl.han.dea.marijn.services;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.User;
import nl.han.dea.marijn.services.subscriptions.SubscriptionServiceREST;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {
    @Mock
    User user;

    @InjectMocks
    SubscriptionServiceREST subscriptionServiceREST;

    @Test
    public void totalAmountTest(){
        int userId = 2;
        double desiredAmount = 15.0;
        JDBC.start();
        user = User.findById(userId);
        JDBC.stop();

        subscriptionServiceREST.setUser(user);
        double amount = subscriptionServiceREST.calculateTotalAmount();
        Assert.assertEquals(amount, desiredAmount, 0.001);
    }
}
