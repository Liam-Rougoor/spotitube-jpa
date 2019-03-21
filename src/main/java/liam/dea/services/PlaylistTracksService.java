package liam.dea.services;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;

public interface PlaylistTracksService {

    TracksOverview getPlaylistTracksOverview(int playlistID, String token);

    TracksOverview getAvailableTracksOverview(int playlistID, String token);

    TracksOverview addTrack(int playlistId, Track track, String token);

    TracksOverview removeTrack(int playlistID, int trackID, String token);
}
