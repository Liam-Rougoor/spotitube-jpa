package liam.dea.persistence;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;

import java.util.List;

public interface TrackDAO {
    Track getTrackByID(int id);

    TracksOverview getPlaylistTracks(int playlistID);

    TracksOverview getAvailableTracks(int playlistID);

    TracksOverview addTrack(int playlistId, Track track);

    TracksOverview removeTrack(int playlistID, int trackID);

    TracksOverview createTracksOverview(List<Track> tracks);
}
