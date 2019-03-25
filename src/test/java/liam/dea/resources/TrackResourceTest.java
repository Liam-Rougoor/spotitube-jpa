package liam.dea.resources;

import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;
import liam.dea.services.PlaylistTracksService;
import liam.dea.services.TokenService;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrackResourceTest {

    @Mock
    private TokenService tokenServiceStub;

    @Mock
    private PlaylistTracksService playlistTracksServiceStub;

    private TracksOverview tracksOverview;
    private List<Track> tracks;

    @InjectMocks
    private TrackResource systemUnderTest;

    @BeforeEach
    void setUp() {
        tracks = new ArrayList<>();
        Track track = new Track();
        track.setId(1);
        track.setTitle("Test Track");
        tracks.add(track);
        tracksOverview = new TracksOverview(tracks);
    }

    @Test
    void returnStatusOKAndTracksOverviewWhenSuccess() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(playlistTracksServiceStub.getAvailableTracksOverview(1)).thenReturn(tracksOverview);

        Response response = systemUnderTest.getAvailableTracksForPlaylist(1, "1234");
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(tracksOverview, response.getEntity());
    }

}
