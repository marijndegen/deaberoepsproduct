package nl.han.dea.marijn.database.models;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.List;

@Table("users")
public class User extends Model {
    public double calculateTotalAmount() {
        String query = "SELECT COUNT(*) AS 'numberOfSubscriptions' \n" +
                "FROM activesubscriptions\n" +
                "WHERE activesubscriptions.user_id = ?";
            Object numberOfSumbscriptions = Base.firstCell(query, this.get("id"));
            if((Long) numberOfSumbscriptions == 0){
                return 0.0;
            }

        query = "SELECT SUM(subscriptions.price)\n" +
                "FROM users\n" +
                "INNER JOIN activesubscriptions ON users.id = activesubscriptions.user_id\n" +
                "INNER JOIN subscriptions ON activesubscriptions.subscription_id = subscriptions.id\n" +
                "GROUP BY users.id\n" +
                "HAVING users.id = ?";
        Object amount = Base.firstCell(query, this.get("id"));
        return (Double) amount;
    }


}
