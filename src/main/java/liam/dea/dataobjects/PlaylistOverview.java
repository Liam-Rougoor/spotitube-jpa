package liam.dea.dataobjects;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.Track;

import java.util.List;

public class PlaylistOverview {

    private Playlist playlist;

    public PlaylistOverview(Playlist playlist) {
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public List<Track> getTracks(){
        return playlist.getTracks();
    }

    public int getLength() {
        return playlist.getLength();
    }
}
