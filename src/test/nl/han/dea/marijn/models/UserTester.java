package nl.han.dea.marijn.models;

import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTester implements CRUDModel{

    @Before
    public void init(){
        JDBC.start();
    }

    @Test
    public void create() {
        User user = new User();
        user.set("user", "marijn");
        user.set("password", "qwerasdfuiop");
        user.save();

        User userToCheck = new User();
        userToCheck = user.findById(user.get("id"));
        Assert.assertEquals( "qwerasdfuiop", userToCheck.get("password"));
        user.delete();
    }

    @Test
    public void read() {
        User user = new User();
        user.set("user", "marijn");
        user.set("password", "qwerasdfuiop");
        user.save();

        User secondUser = User.findFirst("user = ?", "marijn");
        System.out.println(secondUser.get("password"));
        secondUser.delete();
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }

    @After
    public void destroy(){
        JDBC.stop();
    }
}
