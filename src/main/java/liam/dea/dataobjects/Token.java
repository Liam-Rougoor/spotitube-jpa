package liam.dea.dataobjects;

public class Token {
    String user, token, expireDate;

    public Token() {
    }

    public Token(String user, String token, String expireDate) {
        this.user = user;
        this.token = token;
        this.expireDate = expireDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
