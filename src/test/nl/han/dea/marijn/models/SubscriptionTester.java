package nl.han.dea.marijn.models;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.Subscription;
import org.javalite.activejdbc.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubscriptionTester implements CRUDModel {

    private Subscription subscription;
    
    @Before
    public void init(){
        JDBC.start();
    }

    @Test
    public void createAndRead() {
        createSubscription();
        insertSubscriptionData();
        updateSubscription();

        Subscription subToCheck = new Subscription();
        subToCheck = subToCheck.findById(subscription.get("id"));
        assertEquals("vodafone", subToCheck.get("provider"));
        deleteSubscription();
    }

    @Test
    public void update() {
        createSubscription();
        insertSubscriptionData();
        updateSubscription();

        Subscription subToCheck = new Subscription();
        subToCheck = subToCheck.findById(subscription.get("id"));
        subToCheck.set("provider", "ziggo");
        subToCheck.saveIt();
        assertEquals("ziggo", subToCheck.get("provider"));
        deleteSubscription();

    }

    @Test
    public void delete(){
        createSubscription();
        insertSubscriptionData();
        update();
        deleteSubscription();
    }



    @Test(expected = DBException.class)
    public void createWithWrongSubscription(){
        subscription.set("provider", "asdf");
        subscription.set("service", "heel snel internet abbonement!!");
        subscription.set("price", 100.30);
        subscription.set("shareable", 1);
        subscription.saveIt();

        Subscription subToCheck = new Subscription();
        subToCheck = subToCheck.findById(subscription.get("id"));
        assertEquals("vodafone", subToCheck.get("provider"));
    }

    @After
    public void destroy(){
        JDBC.stop();
    }

    private void createSubscription(){
        subscription = new Subscription();
    }

    private void insertSubscriptionData(){
        subscription.set("provider", "vodafone");
        subscription.set("service", "heel snel internet abbonement!!");
        subscription.set("price", 100.30);
        subscription.set("shareable", 1);
    }

    private void updateSubscription(){
        subscription.saveIt();
    }

    private void deleteSubscription(){
        subscription.delete();
    }
}
