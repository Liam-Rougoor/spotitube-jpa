package liam.dea.services;

import liam.dea.dataobjects.User;
import liam.dea.persistence.TokenDAO;
import liam.dea.persistence.UserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DatabaseLoginServiceTest {

    @Mock
    private UserDAO userDAOStub;

    @Mock
    private TokenDAO tokenDAOMock;


    @InjectMocks
    private DatabaseLoginService systemUnderTest;

    @Test
    void callsGetLogin() {
        User user = new User();
        user.setUser("liam");
        Mockito.when(userDAOStub.getUserByNameAndPassword("liam", "pass")).thenReturn(user);
        systemUnderTest.getLogin("liam","pass");
        Mockito.verify(tokenDAOMock).getLogin("liam");
    }
}
