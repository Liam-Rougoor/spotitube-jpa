package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;
import liam.dea.services.PlaylistService;
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
class PlaylistResourceTest {

    @Mock
    private PlaylistService playlistServiceStub;

    @Mock
    private PlaylistTracksService playlistTracksServiceStub;

    @Mock
    private TokenService tokenServiceStub;

    @InjectMocks
    private PlaylistResource systemUnderTest;

    private PlaylistsOverview playlistsOverview;
    private TracksOverview tracksOverview;

    @BeforeEach
    void setUp() {
        List<Playlist> playlists = new ArrayList<>();
        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.setName("Test Playlist");
        playlists.add(playlist);
        playlistsOverview = new PlaylistsOverview(playlists);

        List<Track> tracks = new ArrayList<>();
        Track track = new Track();
        track.setId(1);
        track.setTitle("Test Track");
        tracks.add(track);
        tracksOverview = new TracksOverview(tracks);
    }

    @Test
    void returnStatusOKAndPlaylistsOverviewWhenGetAllPlaylistsSucceeds() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(tokenServiceStub.getUserWithToken("1234")).thenReturn("liam");
        Mockito.when(playlistServiceStub.getPlaylistsOverview("liam")).thenReturn(playlistsOverview);

        Response response = systemUnderTest.getAllPlaylists("1234");
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsOverview, response.getEntity());
    }

    @Test
    void returnStatusOKAndPlaylistsOverviewWhenDeletePlaylistSucceeds() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(tokenServiceStub.getUserWithToken("1234")).thenReturn("liam");
        Mockito.when(playlistServiceStub.getPlaylistsOverview("liam")).thenReturn(playlistsOverview);

        Playlist deletedPlaylist = new Playlist();
        deletedPlaylist.setId(1);
        Mockito.when(playlistServiceStub.deletePlaylist(1)).thenReturn(deletedPlaylist);

        Response response = systemUnderTest.deletePlaylist("1234", 1);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsOverview, response.getEntity());
    }

    @Test
    void returnStatusCREATEDAndPlaylistsOverviewWhenCreatePlaylistSucceeds() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(tokenServiceStub.getUserWithToken("1234")).thenReturn("liam");
        Mockito.when(playlistServiceStub.getPlaylistsOverview("liam")).thenReturn(playlistsOverview);

        Playlist createdPlaylist = new Playlist();
        createdPlaylist.setId(1);
        Mockito.when(playlistServiceStub.createPlaylist(createdPlaylist, "liam")).thenReturn(createdPlaylist);

        Response response = systemUnderTest.createPlaylist("1234", createdPlaylist);
        assertEquals(Response.Status.CREATED, response.getStatusInfo());
        assertEquals(playlistsOverview, response.getEntity());
    }

    @Test
    void returnsStatusOKAndPlaylistsOverviewWhenNameChangeSucceeds() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(tokenServiceStub.getUserWithToken("1234")).thenReturn("liam");
        Mockito.when(playlistServiceStub.getPlaylistsOverview("liam")).thenReturn(playlistsOverview);

        Playlist editedPlaylist = new Playlist();
        editedPlaylist.setId(1);
        Mockito.when(playlistServiceStub.editPlaylist(editedPlaylist)).thenReturn(editedPlaylist);

        Response response = systemUnderTest.editPlaylist("1234", editedPlaylist);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(playlistsOverview, response.getEntity());
    }

    @Test
    void returnsStatusOKAndTracksOverviewWhenGettingTracksSucceeds() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(playlistTracksServiceStub.getPlaylistTracksOverview(1)).thenReturn(tracksOverview);

        Response response = systemUnderTest.getTracksFromPlaylist("1234", 1);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    void returnsStatusCREATEDAndTracksOverviewWhenAddingTrackSucceeds() {
        Track addedTrack = new Track();
        addedTrack.setId(2);
        addedTrack.setTitle("Test Track");
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(playlistTracksServiceStub.addTrack(1, addedTrack)).thenReturn(tracksOverview);

        Response response = systemUnderTest.addTrack("1234", 1, addedTrack);
        assertEquals(Response.Status.CREATED, response.getStatusInfo());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    void returnsStatusOKAndTracksOverviewWhenDeletingTrackSucceeds() {
        Mockito.when(tokenServiceStub.validateToken("1234")).thenReturn(true);
        Mockito.when(playlistTracksServiceStub.removeTrack(1, 1)).thenReturn(tracksOverview);

        Response response = systemUnderTest.removeTrack("1234", 1, 1);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(tracksOverview, response.getEntity());
    }

}
