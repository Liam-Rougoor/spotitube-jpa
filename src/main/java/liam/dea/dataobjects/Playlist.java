package liam.dea.dataobjects;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {

    @Id
    private int id;
    private String name;
    private String user;
    @ManyToMany
    @JoinTable( name = "playlist_track",
                joinColumns = {@JoinColumn(name = "playlist")},
                inverseJoinColumns = {@JoinColumn(name = "track")}
    )
    private List<Track> tracks;
    private boolean owner;

    public Playlist() {
        tracks = new ArrayList<>();
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
