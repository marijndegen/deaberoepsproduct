package nl.han.dea.marijn.models;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTester implements CRUDModel{

    private User user;

    @Before
    public void init(){
        JDBC.start();
    }

    @Test
    public void createAndRead() {
        createUser();
        insertUserData();
        updateUser();
        assertEquals(user.get("password"), "qwerasdfuiop");
        deleteUser();
    }

    @Test
    public void update() {
        createUser();
        insertUserData();
        updateUser();
        user.set("password", "asdf");
        updateUser();
        assertEquals(user.get("password"), "asdf");
        deleteUser();
    }

    @Test
    public void delete() {
        createUser();
        insertUserData();
        updateUser();
        deleteUser();
    }

    @After
    public void destroy(){
        JDBC.stop();
    }

    private void createUser(){
        user = new User();
    }

    private void insertUserData(){
        user.set("user", "kobie");
        user.set("password", "qwerasdfuiop");
    }

    private void updateUser(){
        user.saveIt();
    }

    private void deleteUser(){
        user.delete();
    }
}
