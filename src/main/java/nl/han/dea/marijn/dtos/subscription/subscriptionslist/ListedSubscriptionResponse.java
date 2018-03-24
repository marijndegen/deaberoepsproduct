package nl.han.dea.marijn.dtos.subscription.subscriptionslist;

import nl.han.dea.marijn.dtos.subscription.Subscription;

import java.util.ArrayList;
import java.util.List;

public class ListedSubscriptionResponse {
    /*Arraylist niet geinstantieerd, koste 2 uur om de nullpointer op te zoeken*/
    private List<Subscription> abonnementen = new ArrayList<>();
    private double totalPrice = 0;

    public void addSubscription(Subscription subscription){
        abonnementen.add(subscription);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addSubscriptions(List<Subscription> subscriptions){
        for (Subscription subscription:
             subscriptions) {
            this.addSubscription(subscription);
        }
    }
}
