package liam.dea.dataobjects;

public class Login {

    private User user;
    private String token;

    public Login(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser(){
        return user;
    }

    public String getToken(){
        return token;
    }

}
