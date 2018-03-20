package nl.han.dea.marijn.database.models;

import nl.han.dea.marijn.database.enums.Status;
import nl.han.dea.marijn.database.enums.Verdubbeling;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Table("activesubscriptions")
public class ActiveSubscription extends Model {
    public static void makeNewStandardActiveSubscription(User user, Subscription subscription){
        int userId = (int) user.getId();
        int subscriptionId = (int) subscription.getId();
        String startDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String dubbel = Verdubbeling.STANDAARD.getEnumValue();
        String status = Status.ACTIEF.getEnumValue();

        ActiveSubscription activeSubscription = new ActiveSubscription();
        activeSubscription.set("user_id", userId);
        activeSubscription.set("subscription_id", subscriptionId);
        activeSubscription.set("startDate", startDate);
        activeSubscription.set("dubbel", dubbel);
        activeSubscription.set("status", status);
        activeSubscription.save();
    }
}
