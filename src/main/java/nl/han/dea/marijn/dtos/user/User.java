package nl.han.dea.marijn.dtos.user;

public class User {
    private int id;
    private String name;
    private String email;

    public User(nl.han.dea.marijn.database.models.User user){
        this.id = (Integer) user.getId();
        this.name = (String) user.get("user");
        this.email = (String) user.get("email");
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
