package nl.han.dea.marijn.dtos.Subscriptions;

public class Subscription {
    private int id;
    private String aanbieder;
    private String dienst;

    public Subscription(int id, String aanbieder, String dienst) {
        this.id = id;
        this.aanbieder = aanbieder;
        this.dienst = dienst;
    }

    public Subscription(nl.han.dea.marijn.database.models.Subscription s){
        this.setSubscription(s);
    }


    public void setSubscription(nl.han.dea.marijn.database.models.Subscription s){
        id = (Integer) s.get("id");
        aanbieder = (String) s.get("provider");
        dienst = (String) s.get("service");
    }
}
