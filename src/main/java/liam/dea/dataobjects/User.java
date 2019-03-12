package liam.dea.dataobjects;

public class User {

    private String user, password;

    public User(){}

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        User other = (User)obj;
        if(other == null){
            return false;
        }
        return other.user.equals(user) && other.password.equals(password);
    }
}
