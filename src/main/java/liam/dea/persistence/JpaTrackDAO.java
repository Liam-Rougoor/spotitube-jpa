package liam.dea.persistence;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TrackInPlaylist;
import liam.dea.dataobjects.TrackInPlaylistID;
import liam.dea.dataobjects.TracksOverview;

import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Alternative
public class JpaTrackDAO extends JpaDAO implements TrackDAO {
    private EntityManager entityManager;

    @Override
    public Track getTrackByID(int id) {
        entityManager = super.createEntityManager();
        return entityManager.find(Track.class, id);
    }

    @Override
    public TracksOverview getPlaylistTracks(int playlistID) {
        entityManager = super.createEntityManager();
        return entityManager.find(TracksOverview.class, playlistID);
    }

    @Override //TODO: get available tracks
    public TracksOverview getAvailableTracks(int playlistID) {
        entityManager = super.createEntityManager();
        Query query = entityManager.createQuery("SELECT id " +
                "FROM track " +
                "WHERE id NOT IN (" +
                "SELECT track " +
                "FROM playlist_track " +
                "where playlist = ? " +
                ") ");

        return (TracksOverview) query.getResultList().get(0);
    }

    @Override
    public TracksOverview addTrack(int playlistId, Track track) {
        entityManager = super.createEntityManager();
        TrackInPlaylist trackInPlaylist = new TrackInPlaylist(playlistId, track.getId(), track.getOfflineAvailable());

        entityManager.persist(trackInPlaylist);
        return getPlaylistTracks(playlistId);
    }

    @Override
    public TracksOverview removeTrack(int playlistID, int trackID) {
        entityManager = super.createEntityManager();
        TrackInPlaylistID trackInPlaylistID = new TrackInPlaylistID(playlistID, trackID);
        entityManager.find(TrackInPlaylist.class, trackInPlaylistID);
        return getPlaylistTracks(playlistID);
    }

    @Override
    public TracksOverview createTracksOverview(List<Track> tracks) {
        return new TracksOverview(tracks);
    }
}
