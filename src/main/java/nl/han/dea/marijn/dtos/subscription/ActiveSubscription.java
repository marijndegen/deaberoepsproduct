package nl.han.dea.marijn.dtos.subscription;

import nl.han.dea.marijn.database.enums.Status;
import nl.han.dea.marijn.database.enums.Verdubbeling;

import java.util.Date;

public class ActiveSubscription {

    //todo subscription dto maken. gebaseerd op subscription.
    private long id;
    private int user_id;
    private int subscription_id;
    private String startDate;         //yyMMdd
    private String dubbel;    //Enum
    private String status;          //Enum

    public ActiveSubscription(nl.han.dea.marijn.database.models.ActiveSubscription activeSubscription){
        System.out.println(activeSubscription.get("id"));
        this.id = (long) activeSubscription.get("id");
        this.user_id = (Integer) activeSubscription.get("user_id");
        this.subscription_id = (Integer) activeSubscription.get("subscription_id");
        this.startDate = (String) activeSubscription.get("startDate");
        this.dubbel = (String) activeSubscription.get("dubbel");
        this.status = (String) activeSubscription.get("status");
    }

    public ActiveSubscription(long id, int user_id, int subscription_id) {
        this(id, user_id, subscription_id, new Date().toString(), Verdubbeling.STANDAARD.getEnumValue(), Status.ACTIEF.getEnumValue());
    }

    public ActiveSubscription(long id, int user_id, int subscription_id, String startDate, String dubbel, String status) {
        this.id = id;
        this.user_id = user_id;
        this.subscription_id = subscription_id;
        this.startDate = startDate;
        this.dubbel = dubbel;
        this.status = status;
    }
}
