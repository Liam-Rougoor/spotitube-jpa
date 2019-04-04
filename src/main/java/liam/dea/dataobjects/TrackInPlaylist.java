package liam.dea.dataobjects;

public class TrackInPlaylist {
    int playlistID, trackID;
    boolean offlineAvailable;


    public TrackInPlaylist(int playlistID, int trackID, boolean offlineAvailable) {
        this.playlistID = playlistID;
        this.trackID = trackID;
        this.offlineAvailable = offlineAvailable;
    }
}
