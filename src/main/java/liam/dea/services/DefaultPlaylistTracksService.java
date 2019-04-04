package liam.dea.services;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;
import liam.dea.persistence.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DefaultPlaylistTracksService implements PlaylistTracksService {

    private TrackDAO trackDAO;

    public DefaultPlaylistTracksService() {
    }

    @Inject
    public DefaultPlaylistTracksService(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksOverview getPlaylistTracksOverview(int playlistID) {
        return trackDAO.getPlaylistTracks(playlistID);
    }

    @Override
    public TracksOverview getAvailableTracksOverview(int playlistID) {
        return trackDAO.getAvailableTracks(playlistID);
    }

    @Override
    public TracksOverview addTrack(int playlistId, Track track) {
        return trackDAO.addTrack(playlistId, track);
    }

    @Override
    public TracksOverview removeTrack(int playlistID, int trackID) {
        return trackDAO.removeTrack(playlistID, trackID);
    }
}
