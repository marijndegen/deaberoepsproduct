package nl.han.dea.marijn.services.users;

import nl.han.dea.marijn.database.models.ActiveSubscription;
import nl.han.dea.marijn.dtos.user.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    void addSharedSubscription(ActiveSubscription activeSubscription, nl.han.dea.marijn.database.models.User leach);
}
