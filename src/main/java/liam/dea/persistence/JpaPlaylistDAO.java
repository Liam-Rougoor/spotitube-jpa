package liam.dea.persistence;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Alternative
public class JpaPlaylistDAO extends JpaDAO implements PlaylistDAO {

    private EntityManager entityManager;

    private TrackDAO trackDAO;

    public JpaPlaylistDAO() {
    }

    @Inject
    public JpaPlaylistDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public List<Playlist> getAllPlaylists(String currentUser) {
        this.entityManager = super.createEntityManager();
        Query query = entityManager.createQuery("SELECT p FROM Playlist p");
        return query.getResultList();
    }

    @Override
    public Playlist getPlaylistByID(int id) {
        this.entityManager = super.createEntityManager();
        return entityManager.find(Playlist.class, id);
    }


    @Override
    public Playlist deletePlaylist(int id) {
        this.entityManager = super.createEntityManager();
        Playlist playlistByID = getPlaylistByID(id);
        entityManager.remove(playlistByID);
        entityManager.flush();
        return playlistByID;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, String currentUser) {
        this.entityManager = super.createEntityManager();
        playlist.setUser(currentUser);
        entityManager.persist(playlist);
        entityManager.flush();
        return playlist;
    }

    @Override
    public Playlist editPlaylist(Playlist playlist) {
        Playlist playlistByID = getPlaylistByID(playlist.getId());
        playlistByID = playlist;
        this.entityManager = super.createEntityManager();
        entityManager.merge(playlistByID);
        entityManager.flush();
        return playlist;
    }

    @Override
    public PlaylistsOverview getPlaylistsOverview(String currentUser) {
        return new PlaylistsOverview(getAllPlaylists(currentUser));
    }
}
