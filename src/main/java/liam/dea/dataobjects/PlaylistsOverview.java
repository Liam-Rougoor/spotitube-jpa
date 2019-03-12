package liam.dea.dataobjects;

import liam.dea.dataobjects.Playlist;

import java.util.List;

public class PlaylistsOverview {

    private List<Playlist> playlists;
    private int length;

    public PlaylistsOverview(List<Playlist> playlists){
        this.playlists = playlists;
        length = 600;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
