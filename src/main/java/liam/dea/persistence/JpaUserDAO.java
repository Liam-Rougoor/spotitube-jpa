package liam.dea.persistence;

import liam.dea.dataobjects.User;
import liam.dea.exceptions.InvalidCredentialsException;

import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;

@Alternative
public class JpaUserDAO extends JpaDAO implements UserDAO {

    @Override
    public User getUserByUsername(String name) {
        EntityManager entityManager = super.createEntityManager();
        return entityManager.find(User.class, name);
    }

    @Override
    public User getUserByUsernameAndPassword(String name, String password) {
        User user = getUserByUsername(name);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new InvalidCredentialsException();
    }

}
