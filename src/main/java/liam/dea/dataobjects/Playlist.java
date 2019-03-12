package liam.dea.dataobjects;

import liam.dea.resources.LoginResource;
import liam.dea.stores.TrackStore;
import liam.dea.stores.UserStore;

import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private User user;
    private List<Track> tracks;
    //private int length;

    public Playlist(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
        tracks = TrackStore.getInstance().getTracks();
//        length = 6000;
    }

    public Playlist(int id){
        this.id = id;
        this.name = "Playlist " + id;
        this.user = UserStore.getInstance().getUserByName("liam");
        tracks = TrackStore.getInstance().getTracks();
//        length = 6000;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getOwner(){
        return LoginResource.getActiveLogin().getUser().equals(user);
    }

    public List<Track> getTracks() {
        return tracks;
    }

//    public int getLength(){
//        return length;
//    }

}
