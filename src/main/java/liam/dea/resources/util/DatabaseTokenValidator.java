package liam.dea.resources.util;

import liam.dea.exceptions.InvalidTokenException;
import liam.dea.persistence.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DatabaseTokenValidator implements TokenValidator {

    TokenDAO tokenDAO;

    public DatabaseTokenValidator() {
    }

    @Inject
    public DatabaseTokenValidator(TokenDAO tokenDAO) {
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
