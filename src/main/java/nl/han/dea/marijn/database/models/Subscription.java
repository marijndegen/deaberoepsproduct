package nl.han.dea.marijn.database.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.ArrayList;
import java.util.List;


@Table("subscriptions")
public class Subscription extends Model {
    public static List<nl.han.dea.marijn.dtos.subscription.Subscription> searchSubscription(String filter) {
        List<nl.han.dea.marijn.dtos.subscription.Subscription> subscriptions = new ArrayList<>();
        List<Subscription> results = Subscription.where("subscriptions.service like ?", "%" + filter + "%");
        for (Subscription subscription : results) {
            subscriptions.add(new nl.han.dea.marijn.dtos.subscription.Subscription(subscription));
        }
        return subscriptions;
    }
}
