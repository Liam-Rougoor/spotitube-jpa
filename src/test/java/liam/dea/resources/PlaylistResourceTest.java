package liam.dea.resources;

import liam.dea.dataobjects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistResourceTest {

    private PlaylistResource systemUnderTest;


    @BeforeEach
    void setUp() {
        systemUnderTest = new PlaylistResource();
    }

    @Test
    void returnTracksAndStatusOKIfTokenMatches() {
        LoginResource resource = new LoginResource();
        resource.login(new User("liam", "pass"));
        systemUnderTest.getAllPlaylists("1234-1234-1234");
    }
}
