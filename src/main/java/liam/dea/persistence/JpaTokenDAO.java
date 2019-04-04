package liam.dea.persistence;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.Token;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
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
        Token foundToken = entityManager.find(Token.class, token);
        return foundToken.getUser();
    }

    @Override
    public String createNewTokenForUser(String username) {
        entityManager = super.createEntityManager();
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(username);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(2);
        token.setExpireDate(tomorrow.toString());
        entityManager.persist(token);
        return token.getToken();
    }

    @Override
    public boolean tokenIsValid(String token) {
        entityManager = super.createEntityManager();
        Token foundToken = entityManager.find(Token.class, token);
        return foundToken != null;
    }

    @Override
    public Login getLogin(String user) {
        return new Login(userDAO.getUserByUsername(user).getName(), createNewTokenForUser(user));
    }
}
