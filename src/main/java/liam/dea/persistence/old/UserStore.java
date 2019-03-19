package liam.dea.persistence.old;

import liam.dea.dataobjects.User;

import java.util.ArrayList;
import java.util.List;

public class UserStore {
    private List<User> users;
    private static UserStore instance;

    private UserStore(){
        users = new ArrayList<>();
        users.add(new User("liam", "pass"));
        users.add(new User("liam1", "pass1"));
    }

    public static UserStore getInstance(){
        if(instance==null){
            instance = new UserStore();
        }
        return instance;
    }

    public User getUserByName(String username){
        for(User user : users){
            if(user.getUser().equals(username)){
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
