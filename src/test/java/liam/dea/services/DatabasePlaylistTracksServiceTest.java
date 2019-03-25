package liam.dea.services;

import liam.dea.dataobjects.Track;
import liam.dea.persistence.TrackDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DatabasePlaylistTracksServiceTest {

    @Mock
    private TrackDAO trackDAOMock;

    @InjectMocks
    private DatabasePlaylistTracksService systemUnderTest;

    @Test
    void callsGetPlaylistTracks() {
        systemUnderTest.getPlaylistTracksOverview(1);
        Mockito.verify(trackDAOMock).getPlaylistTracks(1);
    }

    @Test
    void callsGetAvailableTracks() {
        systemUnderTest.getAvailableTracksOverview(1);
        Mockito.verify(trackDAOMock).getAvailableTracks(1);
    }

    @Test
    void callsRemoveTrack() {
        systemUnderTest.removeTrack(1, 1);
        Mockito.verify(trackDAOMock).removeTrack(1,1);

        systemUnderTest.removeTrack(2, 2);
        Mockito.verify(trackDAOMock).removeTrack(2,2);
    }

    @Test
    void callsAddTrack() {
        Track track = new Track();
        systemUnderTest.addTrack(1, track);
        Mockito.verify(trackDAOMock).addTrack(1, track);

        systemUnderTest.addTrack(2, track);
        Mockito.verify(trackDAOMock).addTrack(2, track);
    }
}
