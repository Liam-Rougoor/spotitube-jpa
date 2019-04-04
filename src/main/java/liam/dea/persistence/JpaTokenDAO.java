package liam.dea.persistence;

import liam.dea.dataobjects.Login;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;

@Alternative
public class JpaTokenDAO extends JpaDAO implements TokenDAO {
    private UserDAO userDAO;
    private EntityManager entityManager;

    public JpaTokenDAO() {
    }

    @Inject
    public JpaTokenDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String getUserWithToken(String token) {
        entityManager = super.createEntityManager();
        Login foundLogin = entityManager.find(Login.class, token);
        return foundLogin.getUser();
    }

    @Override
    public String createNewTokenForUser(String username) {
        entityManager = super.createEntityManager();
        Login login = new Login(username, UUID.randomUUID().toString());
        entityManager.persist(login);
        return login.getToken();
    }

    @Override
    public boolean tokenIsValid(String token) {
        entityManager = super.createEntityManager();
        Login foundLogin = entityManager.find(Login.class, token);
        return foundLogin != null;
    }

    @Override
    public Login getLogin(String user) {
        return new Login(userDAO.getUserByUsername(user).getName(), createNewTokenForUser(user));
    }
}
