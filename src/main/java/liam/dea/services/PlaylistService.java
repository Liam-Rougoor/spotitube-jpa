package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;

public interface PlaylistService {

    PlaylistsOverview getPlaylistsOverview(String username);

    void deletePlaylist(int id);

    //TODO find a way to remove token parameter, but use username instead
    void createPlaylist(Playlist playlist, String username);

    void editPlaylist(Playlist playlist);
}
