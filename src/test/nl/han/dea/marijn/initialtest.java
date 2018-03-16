package nl.han.dea.marijn;

import nl.han.dea.marijn.clientpaths.Helloworld;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class initialtest {
    private Helloworld helloworld;

    @Before
    public void initialise(){
        helloworld = new Helloworld();
    }

    @Test
    public void myAwesomeTest(){
        String qwer = helloworld.test();
        Assert.assertEquals(qwer, "test!");
    }
}
