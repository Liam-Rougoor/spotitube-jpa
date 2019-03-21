package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.persistence.PlaylistDAO;

public interface PlaylistService {

    PlaylistsOverview getPlaylistsOverview(String token);

    void deletePlaylist(int id, String token);


    void createPlaylist(Playlist playlist, String token);

    void editPlaylist(Playlist playlist, String token);
}
