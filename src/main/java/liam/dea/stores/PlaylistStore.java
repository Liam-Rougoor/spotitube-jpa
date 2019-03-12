package liam.dea.stores;

import liam.dea.dataobjects.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistStore {

    //TODO: Hashmap?
    private List<Playlist> playlists;

    private static PlaylistStore ourInstance;

    public static PlaylistStore getInstance() {
        if(ourInstance==null){
            ourInstance = new PlaylistStore();
        }
        return ourInstance;
    }

    private PlaylistStore() {
        playlists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            playlists.add(new Playlist(i));
        }
    }

    public List<Playlist> getPlaylists(){
        return playlists;
    }

    public Playlist getPlaylistByID(int id){
        for(Playlist playlist : playlists){
            if(playlist.getId() == id){
                return playlist;
            }
        }
        return null;
    }

    public void add(Playlist playlist){
        playlists.add(playlist);
    }

    public void delete(Playlist playlist){
        playlists.remove(playlist);
    }

    public void delete(int playlistID){
        for(Playlist playlist : playlists){
            if(playlist.getId() == playlistID){
                delete(playlist);
                return;
            }
        }
    }
}
