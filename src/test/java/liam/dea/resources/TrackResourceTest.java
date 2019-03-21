package liam.dea.resources;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;
import liam.dea.persistence.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrackResourceTest {

    @Mock
    private TrackDAO trackDAOStub;

    @InjectMocks
    private TrackResource systemUnderTest;

    private List<Track> tracksStub;
    private TracksOverview tracksOverviewStub;
    private Track trackStub;

    @BeforeEach
    void setUp() {
        tracksStub = new ArrayList<>();
        trackStub = new Track();
        trackStub.setId(1);
        trackStub.setTitle("Track 1");
        tracksStub.add(trackStub);
        tracksOverviewStub = new TracksOverview(tracksStub);
    }

    @Test
    void returnsTracksAndStatusOKWhenSuccess() {
        int playlistID = 1;
        String token = "123";
        Mockito.when(trackDAOStub.getAvailableTracks(playlistID)).thenReturn(tracksStub);
        Mockito.when(trackDAOStub.createTracksOverview(tracksStub)).thenReturn(tracksOverviewStub);

        Response response = systemUnderTest.getAvailableTracksForPlaylist(playlistID, token);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        TracksOverview actualTracksOverview = (TracksOverview) response.getEntity();
        Track actualTrack = actualTracksOverview.getTracks().get(0);
        assertEquals(trackStub.getId(), actualTrack.getId());
        assertEquals(trackStub.getTitle(), actualTrack.getTitle());
    }
}
