package liam.dea.persistence;

import liam.dea.dataobjects.User;

public interface UserDAO {
    User getUserByName(String name);

    User getUserByNameAndPassword(String name, String password);
}
