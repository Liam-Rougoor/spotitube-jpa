package liam.dea.services;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;
import liam.dea.persistence.DefaultTrackDAO;
import liam.dea.persistence.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DatabasePlaylistTracksService implements PlaylistTracksService {

    private TrackDAO trackDAO;

    public DatabasePlaylistTracksService() {
    }

    @Inject
    public DatabasePlaylistTracksService(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksOverview getPlaylistTracksOverview(int playlistID) {
        return new TracksOverview(trackDAO.getPlaylistTracks(playlistID));
    }

    @Override
    public TracksOverview getAvailableTracksOverview(int playlistID) {
        return new TracksOverview(trackDAO.getAvailableTracks(playlistID));
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
