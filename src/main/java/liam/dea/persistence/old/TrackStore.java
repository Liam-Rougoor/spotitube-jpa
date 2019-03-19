package liam.dea.persistence.old;

import liam.dea.dataobjects.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackStore {

    private static TrackStore instance;
    private List<Track> tracks;


    private TrackStore(){
        tracks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tracks.add(new Track(i));
        }
    }

    public static TrackStore getInstance() {
        if(instance == null){
            instance = new TrackStore();
        }
        return instance;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
