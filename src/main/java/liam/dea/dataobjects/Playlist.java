package liam.dea.dataobjects;

import liam.dea.persistence.PlaylistDAO;
import liam.dea.resources.LoginResource;
import liam.dea.persistence.old.TrackStore;
import liam.dea.persistence.old.UserStore;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private String user;
    private List<Track> tracks;
    private boolean owner;

//    public Playlist(int id, String name, String user) {
//        this.id = id;
//        this.name = name;
//        this.user = user;
//        tracks = TrackStore.getInstance().getTracks();
//    }

    public Playlist() {
        tracks = new ArrayList<>();
    }

//    public Playlist(int id) {
//        this.id = id;
//        this.name = "Playlist " + id;
//        this.user = "liam1";
//        tracks = TrackStore.getInstance().getTracks();
//    }

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public int getLength() {
        int length = 0;
        for (Track track : tracks) {
            length += track.getDuration();
        }
        return length;
    }

}
