package liam.dea.resources;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.persistence.TokenDAO;
import liam.dea.persistence.UserDAO;
import liam.dea.resources.LoginResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {


    @Mock
    private TokenDAO tokenDAOStub;

    @Mock
    private UserDAO userDAOStub;

    @InjectMocks
    private LoginResource systemUnderTest;

    private User userStub;

    @BeforeEach
    void setUp() {
        userStub = new User();
    }

    @Test
    void returnsStatusCreatedAndCorrectLoginIfCorrectCredentialsAreFilledIn() {
        userStub.setUser("liam");
        userStub.setPassword("pass");

        //TODO Uwe vragen waarom hier normale code wordt uitgevoerd.
        Mockito.when(userDAOStub.getUserByNameAndPassword("liam","pass")).thenReturn(userStub);

        //Deze werkt wel
        Mockito.when(tokenDAOStub.getLogin("liam")).thenReturn(new Login("liam", "1234-1234-1234"));

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
