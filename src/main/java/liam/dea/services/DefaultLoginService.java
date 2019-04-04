package liam.dea.services;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.persistence.TokenDAO;
import liam.dea.persistence.UserDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DefaultLoginService implements LoginService {

    private UserDAO userDAO;
    private TokenDAO tokenDAO;

    public DefaultLoginService() {
    }

    @Inject
    public DefaultLoginService(UserDAO userDAO, TokenDAO tokenDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
    }

    @Override
    public Login getLogin(String user, String password) {
        User foundUser = userDAO.getUserByUsernameAndPassword(user, password);
        return tokenDAO.getLogin(foundUser.getUser());
    }
}
