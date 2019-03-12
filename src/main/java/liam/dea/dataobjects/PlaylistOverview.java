package liam.dea.dataobjects;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.Track;

import java.util.List;

public class PlaylistOverview {

    private Playlist playlist;
    private int length;

    public PlaylistOverview(Playlist playlist) {
        this.playlist = playlist;
        length = 500;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public List<Track> getTracks(){
        return playlist.getTracks();
    }

    public int getLength() {
        return length;
    }
}
