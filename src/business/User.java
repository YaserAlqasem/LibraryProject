package business;

public class User {
    private String id;
    private String password;
    private Role role;

    public User(String id, String password, Role role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }
}
