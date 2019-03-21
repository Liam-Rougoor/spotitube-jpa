package liam.dea.services;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;

public interface PlaylistTracksService {

    TracksOverview getPlaylistTracksOverview(int playlistID);

    TracksOverview getAvailableTracksOverview(int playlistID);

    TracksOverview addTrack(int playlistId, Track track);

    TracksOverview removeTrack(int playlistID, int trackID);
}
