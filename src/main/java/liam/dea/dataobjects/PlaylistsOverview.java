package liam.dea.dataobjects;

import liam.dea.dataobjects.Playlist;

import java.util.List;

public class PlaylistsOverview {

    private List<Playlist> playlists;

    public PlaylistsOverview(List<Playlist> playlists){
        this.playlists = playlists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        int length = 0;
        for(Playlist playlist : playlists){
            length += playlist.getLength();
        }
        return length;
    }
}
