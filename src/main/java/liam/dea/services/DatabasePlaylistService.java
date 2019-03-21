package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.persistence.PlaylistDAO;

import javax.enterprise.inject.Default;

@Default
public class DatabasePlaylistService implements PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    @Override
    public PlaylistsOverview getPlaylistsOverview(String token) {
        return playlistDAO.getPlaylistsOverview(token);
    }

    @Override
    public void deletePlaylist(int id, String token) {
        playlistDAO.deletePlaylist(id, token);
    }

    @Override
    public void createPlaylist(Playlist playlist, String token) {
        playlistDAO.addPlaylist(playlist, token);
    }

    @Override
    public void editPlaylist(Playlist playlist, String token) {
        playlistDAO.editPlaylist(playlist, token);
    }
}
