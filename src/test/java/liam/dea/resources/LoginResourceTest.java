package liam.dea.resources;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.resources.LoginResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class LoginResourceTest {

    private LoginResource systemUnderTest;

    @BeforeEach
    void setUp() {
        systemUnderTest = new LoginResource();
    }

    @Test
    void returnsStatusCreatedAndCorrectLoginIfCorrectCredentialsAreFilledIn() {
        User user = new User("liam", "pass");
        Response response = systemUnderTest.login(user);
        assertEquals(response.getStatusInfo(), Response.Status.CREATED);

        Login login = (Login) response.getEntity();
        assertEquals(user, login.getUser());
        assertEquals("1234-1234-1234", login.getToken());
    }

    @Test
    void returnsStatusUnauthorizedIfIncorrectUsernameButCorrectPasswordAreFilledIn(){
        User user = new User("wrongUser", "pass");
        Response response = systemUnderTest.login(user);
        assertEquals(response.getStatusInfo(), Response.Status.UNAUTHORIZED);
    }

    @Test
    void returnsStatusUnauthorizedIfCorrectUsernameAndIncorrectPasswordAreFilledIn() {
        User user = new User("user", "wrongPass");
        Response response = systemUnderTest.login(user);
        assertEquals(response.getStatusInfo(), Response.Status.UNAUTHORIZED);
    }

    @Test
    void returnsStatusUnauthorizedIfIncorrectUsernameAndIncorrectPasswordAreFilledIn(){
        User user = new User("wrongUser", "wrongPass");
        Response response = systemUnderTest.login(user);
        assertEquals(response.getStatusInfo(), Response.Status.UNAUTHORIZED);
    }
}
