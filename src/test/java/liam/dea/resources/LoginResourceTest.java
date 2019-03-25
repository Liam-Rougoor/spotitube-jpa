package liam.dea.resources;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.services.LoginService;
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
    private LoginService loginServiceStub;

    @InjectMocks
    private LoginResource systemUnderTest;

    @Test
    void returnResponseCreatedWhenLoginIsOK() {
        User user = new User();
        user.setUser("liam");
        user.setPassword("pass");
        user.setName("Liam Rougoor");

        Login login = new Login("Liam Rougoor", "1234");

        Mockito.when(loginServiceStub.getLogin("liam","pass")).thenReturn(login);


        Response response = systemUnderTest.login(user);
        assertEquals(Response.Status.CREATED, response.getStatusInfo());
        assertEquals(login, response.getEntity());
    }
}
