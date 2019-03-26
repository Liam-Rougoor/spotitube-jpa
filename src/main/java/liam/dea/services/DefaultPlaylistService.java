package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.persistence.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DefaultPlaylistService implements PlaylistService {

    private PlaylistDAO playlistDAO;

    public DefaultPlaylistService() {
    }

    @Inject
    public DefaultPlaylistService(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsOverview getPlaylistsOverview(String username) {
        return playlistDAO.getPlaylistsOverview(username);
    }

    @Override
    public Playlist deletePlaylist(int id) {
        return playlistDAO.deletePlaylist(id);
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, String username) {
        return playlistDAO.createPlaylist(playlist, username);
    }

    @Override
    public Playlist editPlaylist(Playlist playlist) {
        return playlistDAO.editPlaylist(playlist);
    }
}
