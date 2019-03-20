package liam.dea.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class TracksOverview {
    private List<Track> tracks;

    public TracksOverview(List<Track> tracks) {
        this.tracks = tracks;
    }

    public TracksOverview(Playlist playlist){
        this.tracks = playlist.getTracks();
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
