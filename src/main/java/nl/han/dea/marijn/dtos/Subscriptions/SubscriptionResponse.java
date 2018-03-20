package nl.han.dea.marijn.dtos.Subscriptions;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionResponse {
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
}
