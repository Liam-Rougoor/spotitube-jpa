package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistOverview;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.persistence.PlaylistDAO;
import liam.dea.persistence.TokenDAO;
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
    private TokenDAO tokenDAOStub;

    @Mock
    private PlaylistDAO playlistDAOStub;

    @InjectMocks
    private PlaylistResource systemUnderTest;

    private Playlist playlistStub;
    private List<Playlist> playlistsStub;

    @BeforeEach
    void setUp() {
        playlistsStub = new ArrayList<>();
        playlistStub = new Playlist();
        playlistStub.setName("Playlist");
        playlistStub.setId(1);
        playlistStub.setUser("liam1");
        playlistsStub.add(playlistStub);
    }

    @Test
    void returnPlaylistsOverviewAndStatusOKIfTokenMatches() {
        String token = "123";
        Mockito.when(tokenDAOStub.getUserWithToken(token)).thenReturn("liam");
        Mockito.when(playlistDAOStub.getAllPlaylists(token)).thenReturn(playlistsStub);

        Response response = systemUnderTest.getAllPlaylists(token);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        PlaylistsOverview playlistsOverview = (PlaylistsOverview) response.getEntity();
        List<Playlist> playlists = playlistsOverview.getPlaylists();
        assertEquals(1, playlists.size());
        Playlist playlist = playlists.get(0);
        assertEquals(1, playlist.getId());
        assertEquals("liam1", playlist.getUser());
        assertEquals("Playlist", playlist.getName());
    }

    @Test
    void returnPlaylistOverviewAndStatusOKIfTokenMatches() {
        String token = "123";
        Mockito.when(playlistDAOStub.getPlaylistByID(1, token)).thenReturn(playlistStub);

        Response response = systemUnderTest.getTracksFromPlaylist("123", 1);
        assertEquals(Response.Status.OK, response.getStatusInfo());
        PlaylistOverview playlistOverview = (PlaylistOverview) response.getEntity();
        Playlist playlist = playlistOverview.getPlaylist();
        assertEquals(1, playlist.getId());
        assertEquals("liam1", playlist.getUser());
        assertEquals("Playlist", playlist.getName());
    }
}
