package liam.dea;

import liam.dea.resources.HomeResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

class HomeTest {

    private HomeResource systemUnderTest;

    @BeforeEach
    void setUp() {
        systemUnderTest = new HomeResource();
    }

    @Test
    void respondWithStatusOKAndHelloWorld() {
        Response response = systemUnderTest.home();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals("Hello World", response.getEntity());
    }
}
