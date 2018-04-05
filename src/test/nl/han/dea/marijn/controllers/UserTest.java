package nl.han.dea.marijn.controllers;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.services.subscriptions.SubscriptionService;
import nl.han.dea.marijn.services.users.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import nl.han.dea.marijn.database.models.User;


import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private UserService userService;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getAllUsersTest200(){
        int userId = 2;
        JDBC.start();
        User user = User.findById(userId);
        String token = (String) user.get("token");
        List<nl.han.dea.marijn.dtos.user.User> userDtos = new ArrayList<>(Arrays.asList(new nl.han.dea.marijn.dtos.user.User(1,"eengebruiker", "asdf@qwer.zxvc")));
        JDBC.stop();

        Mockito.when(subscriptionService.isValidUser(token)).thenReturn(true);
        Mockito.when(userService.allUsers()).thenReturn(userDtos);

        Response response = userController.allAbonnees(token);

        Mockito.verify(subscriptionService, Mockito.times(1)).isValidUser(token);
        Mockito.verify(userService, Mockito.times(1)).allUsers();
        Assert.assertEquals(200, response.getStatus());
    }


}
