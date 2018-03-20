package nl.han.dea.marijn.models;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.Subscription;
import org.javalite.activejdbc.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubscriptionTester implements CRUDModel {

    @Before
    public void init(){
        JDBC.start();
    }

    @Test
    public void create() {
        Subscription subToCreate = new Subscription();
        subToCreate.set("provider", "vodafone");
        subToCreate.set("service", "heel snel internet abbonement!!");
        subToCreate.set("price", 100.30);
        subToCreate.set("shareable", 1);
        subToCreate.save();

        Subscription subToCheck = new Subscription();
        subToCheck = subToCheck.findById(subToCreate.get("id"));
        assertEquals("vodafone", subToCheck.get("provider"));
        subToCreate.delete();
    }

    @Test
    public void read() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }

    @Test(expected = DBException.class)
    public void createWithWrongSubscription(){
        Subscription subToCreate = new Subscription();
        subToCreate.set("provider", "asdf");
        subToCreate.set("service", "heel snel internet abbonement!!");
        subToCreate.set("price", 100.30);
        subToCreate.set("shareable", 1);
        subToCreate.save();

        Subscription subToCheck = new Subscription();
        subToCheck = subToCheck.findById(subToCreate.get("id"));
        assertEquals("vodafone", subToCheck.get("provider"));
    }

    @After
    public void destroy(){
        JDBC.stop();
    }
}
