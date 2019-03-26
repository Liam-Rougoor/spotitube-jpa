package liam.dea.persistence;

import liam.dea.dataobjects.User;

public interface UserDAO {
    User getUserByUsername(String name);

    User getUserByUsernameAndPassword(String name, String password);
}
