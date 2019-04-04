package liam.dea.dataobjects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {

    private String user;
    @Id
    private String token;

    public Login(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public Login() {
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }


}
