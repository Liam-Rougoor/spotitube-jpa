package liam.dea.services;

import liam.dea.exceptions.InvalidTokenException;
import liam.dea.persistence.TokenDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DatabaseTokenServiceTest {

    @Mock
    private TokenDAO tokenDAOMock;

    @InjectMocks
    private DatabaseTokenService systemUnderTest;

    @Test
    void returnsTrueIfTokenIsValid() {
        Mockito.when(tokenDAOMock.tokenIsValid("1234")).thenReturn(true);
        assertTrue(systemUnderTest.validateToken("1234"));
    }

    @Test
    void throwsInvalidTokenExceptionIfTokenIsInvalid() {
        Mockito.when(tokenDAOMock.tokenIsValid("1234")).thenReturn(false);
        try{
            systemUnderTest.validateToken("1234");
            fail();
        }catch (InvalidTokenException exception){
        }
    }

    @Test
    void callsGetUserWithToken() {
        systemUnderTest.getUserWithToken("1234");
        Mockito.verify(tokenDAOMock).getUserWithToken("1234");
    }
}
