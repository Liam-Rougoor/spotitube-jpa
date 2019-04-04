package liam.dea.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaDAO {
    protected EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
    }

}
