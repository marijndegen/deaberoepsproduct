package nl.han.dea.marijn.database.models;

import nl.han.dea.marijn.database.enums.Status;
import nl.han.dea.marijn.database.enums.Verdubbeling;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Table("activesubscriptions")
public class ActiveSubscription extends Model {
    public static ActiveSubscription makeNewStandardActiveSubscription(User user, Subscription subscription){
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
        return activeSubscription;
    }

    public static List<nl.han.dea.marijn.dtos.subscription.Subscription> getActiveSubscriptions(Integer userId){
        List<nl.han.dea.marijn.dtos.subscription.Subscription> subscriptionsDtos = new ArrayList<>();
        List<ActiveSubscription> activeSubscriptions = ActiveSubscription.where("user_id = ?", userId);
        for (ActiveSubscription activeSubscription: activeSubscriptions
             ) {
            Subscription subscription = Subscription.findById(activeSubscription.get("subscription_id"));
            subscriptionsDtos.add(new nl.han.dea.marijn.dtos.subscription.Subscription((Integer) activeSubscription.getId(),(String) subscription.get("provider"),(String) subscription.get("service")));
        }
        return subscriptionsDtos;
    }


}
