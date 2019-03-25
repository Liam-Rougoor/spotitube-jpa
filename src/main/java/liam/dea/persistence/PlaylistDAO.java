package liam.dea.persistence;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;

import java.util.List;

public interface PlaylistDAO {
    List<Playlist> getAllPlaylists(String currentUser);

    Playlist getPlaylistByID(int id);

    Playlist deletePlaylist(int id);

    Playlist createPlaylist(Playlist playlist, String user);

    Playlist editPlaylist(Playlist playlist);

    PlaylistsOverview getPlaylistsOverview(String currentUser);
}
