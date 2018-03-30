package nl.han.dea.marijn.dtos.subscription.subscription;

import nl.han.dea.marijn.database.enums.Verdubbeling;
import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.database.models.Subscription;

public class GetSubscriptionResponse {
    private int id;
    private String aanbieder;
    private String dienst;
    private String prijs;
    private String startDatum;
    private String verdubbeling;
    private boolean deelbaar;
    private String status;

    public void addActiveSubscription(ActiveSubscription activeSubscription) {
        this.id = (Integer) activeSubscription.get("id");
        this.startDatum = activeSubscription.get("startDate").toString();
        this.verdubbeling = (String) activeSubscription.get("dubbel");
        this.status = (String) activeSubscription.get("status");
    }

    public void addSubscription(Subscription subscription) {
        this.aanbieder = (String) subscription.get("provider");
        this.dienst = (String) subscription.get("service");
        double kosten = (Double) subscription.get("price");
        if(Verdubbeling.VERDUBBELD.getEnumValue().equals(verdubbeling)){
            kosten *= 2;
        }
        this.prijs = ("€" + kosten + " Per maand");
        this.deelbaar = (Boolean) subscription.get("shareable");
    }


}
