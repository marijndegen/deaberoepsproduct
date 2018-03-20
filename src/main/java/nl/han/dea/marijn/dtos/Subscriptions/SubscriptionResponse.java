package nl.han.dea.marijn.dtos.Subscriptions;

import java.util.List;

public class SubscriptionResponse {
    private List<Subscription> abonnementen;
    private double totalPrice;

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
