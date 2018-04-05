package nl.han.dea.marijn.services;

import nl.han.dea.marijn.services.login.LoginServiceREST;
import nl.han.dea.marijn.services.token.UUIDGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    @Mock
    UUIDGenerator uuidGenerator;

    @InjectMocks
    LoginServiceREST loginService;

    @Test
    public void doLoginTest(){
        Mockito.when(uuidGenerator.generateToken()).thenReturn("aaaa-bbbb-cccc-dddd-eeeeeee");
        boolean status = loginService.doLogin("bassie", "adriaan");
        Assert.assertTrue(status);
    }
}
