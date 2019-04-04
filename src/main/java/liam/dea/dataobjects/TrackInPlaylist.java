package liam.dea.dataobjects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrackInPlaylist {
    @Id
    int playlistID, trackID;
    boolean offlineAvailable;


    public TrackInPlaylist(int playlistID, int trackID, boolean offlineAvailable) {
        this.playlistID = playlistID;
        this.trackID = trackID;
        this.offlineAvailable = offlineAvailable;
    }

    public TrackInPlaylist() {
    }
}
