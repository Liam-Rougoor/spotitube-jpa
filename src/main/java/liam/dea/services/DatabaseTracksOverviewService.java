package liam.dea.services;

import liam.dea.dataobjects.TracksOverview;
import liam.dea.persistence.TrackDAO;

import javax.enterprise.inject.Default;

@Default
public class DatabaseTracksOverviewService implements TracksOverviewService {

    private TrackDAO trackDAO = new TrackDAO();

    @Override
    public TracksOverview getTracksOverview(int playlistID, String token) {
        return new TracksOverview(trackDAO.getAvailableTracks(playlistID, token));
    }
}
