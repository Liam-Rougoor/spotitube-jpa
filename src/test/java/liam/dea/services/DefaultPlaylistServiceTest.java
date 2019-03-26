package liam.dea.services;

import liam.dea.dataobjects.Playlist;
import liam.dea.persistence.PlaylistDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DefaultPlaylistServiceTest {

    @Mock
    private PlaylistDAO playlistDAOMock;

    @InjectMocks
    private DefaultPlaylistService systemUnderTest;

    @Test
    void callsGetPlaylistOverview() {
        systemUnderTest.getPlaylistsOverview("liam");
        Mockito.verify(playlistDAOMock).getPlaylistsOverview("liam");
    }

    @Test
    void callsDeletePlaylist() {
        systemUnderTest.deletePlaylist(1);
        Mockito.verify(playlistDAOMock).deletePlaylist(1);
    }

    @Test
    void callsCreatePlaylist() {
        Playlist playlist = new Playlist();
        systemUnderTest.createPlaylist(playlist, "liam");
        Mockito.verify(playlistDAOMock).createPlaylist(playlist, "liam");
    }

    @Test
    void callsEditPlaylist() {
        Playlist playlist = new Playlist();
        systemUnderTest.editPlaylist(playlist);
        Mockito.verify(playlistDAOMock).editPlaylist(playlist);
    }
}
