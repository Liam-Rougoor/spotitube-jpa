package liam.dea.services;

import liam.dea.exceptions.InvalidTokenException;
import liam.dea.persistence.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DefaultTokenService implements TokenService {

    private TokenDAO tokenDAO;

    public DefaultTokenService() {
    }

    @Inject
    public DefaultTokenService(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public boolean validateToken(String token) {
        if (!tokenDAO.tokenIsValid(token)) {
            throw new InvalidTokenException();
        }
        return true;
    }

    @Override
    public String getUserWithToken(String token) {
        return tokenDAO.getUserWithToken(token);
    }
}
