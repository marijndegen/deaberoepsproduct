package nl.han.dea.marijn.database.models;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Table("subscriptions")
public class Subscription extends Model {

    public static List<Subscription> getUserSubscriptions(Integer id) {
        String sql = "SELECT subscriptions.* \n" +
                "FROM activesubscriptions \n" +
                "INNER JOIN subscriptions ON activesubscriptions.subscription_id = subscriptions.id \n" +
                "WHERE activesubscriptions.user_id = ?";
        return Subscription.findBySQL(sql,id);


    }
}
