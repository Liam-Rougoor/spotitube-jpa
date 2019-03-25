package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;

public interface PlaylistService {

    PlaylistsOverview getPlaylistsOverview(String username);

    Playlist deletePlaylist(int id);

    Playlist createPlaylist(Playlist playlist, String username);

    Playlist editPlaylist(Playlist playlist);
}
