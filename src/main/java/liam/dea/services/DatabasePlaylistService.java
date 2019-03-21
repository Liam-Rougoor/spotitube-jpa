package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.persistence.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DatabasePlaylistService implements PlaylistService {

    private PlaylistDAO playlistDAO;

    public DatabasePlaylistService() {
    }

    @Inject
    public DatabasePlaylistService(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsOverview getPlaylistsOverview(String username) {
        return playlistDAO.getPlaylistsOverview(username);
    }

    @Override
    public void deletePlaylist(int id) {
        playlistDAO.deletePlaylist(id);
    }

    //TODO replace token with username
    @Override
    public void createPlaylist(Playlist playlist, String username) {
        playlistDAO.addPlaylist(playlist, username);
    }

    @Override
    public void editPlaylist(Playlist playlist) {
        playlistDAO.editPlaylist(playlist);
    }
}
