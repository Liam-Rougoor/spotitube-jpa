package liam.dea.dataobjects;

public class Login {

    private String user;
    private String token;

    public Login(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

}
