package nl.han.dea.marijn.services.users;


import nl.han.dea.marijn.database.config.JDBC;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.SharedSubscription;
import nl.han.dea.marijn.database.models.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceREST implements UserService {
    public List<nl.han.dea.marijn.dtos.user.User> allUsers() {
        List<nl.han.dea.marijn.dtos.user.User> userDtos = new ArrayList<>();
        JDBC.start();
        List<User> users = User.findAll();
        for (User user : users) {
            userDtos.add(new nl.han.dea.marijn.dtos.user.User(user));
        }
        JDBC.stop();
        return userDtos;
    }

    @Override
    public void addSharedSubscription(ActiveSubscription activeSubscription, User leach) {
        JDBC.start();
        SharedSubscription sharedSubscription = new SharedSubscription();
        sharedSubscription.set("activesubscription_id", activeSubscription.getId());
        sharedSubscription.set("user_id", leach.getId());
        try{
            sharedSubscription.save();
        }catch (org.javalite.activejdbc.DBException e){
            System.out.println(e.getMessage());
        }
        JDBC.stop();
    }
}
