package liam.dea.services;

import liam.dea.dataobjects.TracksOverview;

public interface TracksOverviewService {
    TracksOverview getTracksOverview(int playlistID, String token);
}
