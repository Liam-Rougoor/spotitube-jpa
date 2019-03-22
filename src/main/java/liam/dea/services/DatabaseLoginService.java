package liam.dea.services;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.persistence.DefaultTokenDAO;
import liam.dea.persistence.TokenDAO;
import liam.dea.persistence.UserDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class DatabaseLoginService implements LoginService {

    private UserDAO userDAO = new UserDAO();
    private TokenDAO tokenDAO;

    @Inject
    public DatabaseLoginService(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public Login getLogin(String user, String password) {
        User foundUser = userDAO.getUserByNameAndPassword(user, password);
        return tokenDAO.getLogin(foundUser.getUser());
    }
}
