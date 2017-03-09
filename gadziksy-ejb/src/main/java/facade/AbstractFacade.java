package facade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by gadzik on 09.03.17.
 */
public class AbstractFacade {

    @PersistenceContext
    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
