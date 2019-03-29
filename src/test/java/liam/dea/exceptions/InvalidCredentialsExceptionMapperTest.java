package liam.dea.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionMapperTest {

    private InvalidCredentialsExceptionMapper systemUnderTest;
    private InvalidCredentialsException invalidCredentialsException;

    @BeforeEach
    void setUp() {
        systemUnderTest = new InvalidCredentialsExceptionMapper();
        invalidCredentialsException = new InvalidCredentialsException();
    }

    @Test
    void respondWithStatusCodeUNAUTHORIZEDAndErrorMessage() {
        Response response = systemUnderTest.toResponse(invalidCredentialsException);
        assertEquals(Response.Status.UNAUTHORIZED, response.getStatusInfo());
        assertEquals("Credentials are invalid", response.getEntity());
    }
}
