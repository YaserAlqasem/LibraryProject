package business;

import java.io.Serial;
import java.io.Serializable;

public class User  implements Serializable {
    @Serial
    private static final long serialVersionUID = 7031028055963894240L;
    private String id;
    private String password;
    private Role role;

    public User(String id, String password, Role role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }
}
