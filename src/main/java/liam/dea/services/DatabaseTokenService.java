package liam.dea.services;

import liam.dea.exceptions.InvalidTokenException;
import liam.dea.persistence.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DatabaseTokenService implements TokenService {

    private TokenDAO tokenDAO;

    public DatabaseTokenService() {
    }

    @Inject
    public DatabaseTokenService(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public void validateToken(String token) {
        if (!tokenDAO.tokenIsValid(token)) {
            throw new InvalidTokenException();
        }
    }

    @Override
    public String getUserWithToken(String token) {
        return tokenDAO.getUserWithToken(token);
    }
}
