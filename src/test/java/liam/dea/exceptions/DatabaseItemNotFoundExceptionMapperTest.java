package liam.dea.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseItemNotFoundExceptionMapperTest {

    private DatabaseItemNotFoundExceptionMapper systemUnderTest;
    private DatabaseItemNotFoundException databaseItemNotFoundException;

    @BeforeEach
    void setUp() {
        systemUnderTest = new DatabaseItemNotFoundExceptionMapper();
        databaseItemNotFoundException = new DatabaseItemNotFoundException("Item not found!");
    }

    @Test
    void respondWithStatusCodeNOTFOUNDAndErrorMessage() {
        Response response = systemUnderTest.toResponse(databaseItemNotFoundException);
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
        assertEquals("Item not found!", response.getEntity());
    }
}
