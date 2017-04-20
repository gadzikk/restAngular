package facade;

import facadeApi.OperationRepository;
import jpa.Operation;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 19.04.17.
 */
@Stateless
public class OperationFacade extends AbstractFacade implements OperationRepository {

    @Override
    public List<Operation> getAllOperations(Long id) {
        TypedQuery<Operation> query = entityManager.createQuery("SELECT O FROM operation O where O.account.id=:id", Operation.class)
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Optional<Operation> getLastOperation(Long id) {
        TypedQuery<Operation> query = entityManager.createQuery("SELECT O FROM operation O where O.account.id=:id", Operation.class)
                .setParameter("id", id);

        if (query.getResultList().isEmpty()) {
            return Optional.empty();
        }
        int lastElement = query.getResultList().size() - 1;
        return Optional.ofNullable(query.getResultList().get(lastElement));
    }
}
