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

@ExtendWith(MockitoExtension.class)
class DefaultLoginServiceTest {

    @Mock
    private UserDAO userDAOStub;

    @Mock
    private TokenDAO tokenDAOMock;


    @InjectMocks
    private DefaultLoginService systemUnderTest;

    @Test
    void callsGetLogin() {
        User user = new User();
        user.setUser("liam");
        Mockito.when(userDAOStub.getUserByUsernameAndPassword("liam", "pass")).thenReturn(user);
        systemUnderTest.getLogin("liam", "pass");
        Mockito.verify(tokenDAOMock).getLogin("liam");
    }
}
