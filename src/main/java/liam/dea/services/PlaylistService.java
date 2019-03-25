package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;

public interface PlaylistService {

    PlaylistsOverview getPlaylistsOverview(String username);

    void deletePlaylist(int id);

    void createPlaylist(Playlist playlist, String username);

    void editPlaylist(Playlist playlist);
}
