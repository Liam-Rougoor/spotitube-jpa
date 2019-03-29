package liam.dea.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class InvalidTokenExceptionMapperTest {

    private InvalidTokenExceptionMapper systemUnderTest;
    private InvalidTokenException invalidTokenException;

    @BeforeEach
    void setUp() {
        systemUnderTest = new InvalidTokenExceptionMapper();
        invalidTokenException = new InvalidTokenException();
    }

    @Test
    void respondWithStatusCodeFORBIDDENAndErrorMessage() {
        Response response = systemUnderTest.toResponse(invalidTokenException);
        assertEquals(Response.Status.FORBIDDEN, response.getStatusInfo());
        assertEquals("Invalid token", response.getEntity());
    }
}
