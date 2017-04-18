package facade;

import facadeApi.TransferRepository;
import jpa.Transfer;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 18.04.17.
 */
@Stateless
public class TransferFacade extends AbstractFacade implements TransferRepository {

    @Override
    public void writeTransfer(Transfer transfer) {
        entityManager.persist(transfer);
    }

    @Override
    public Optional<Transfer> getTransferById(Long id) {
        return Optional.ofNullable(entityManager.find(Transfer.class, id));
    }

    @Override
    public List<Transfer> getAllTransfers() {
        TypedQuery<Transfer> query = entityManager.createQuery("SELECT X FROM transfer X", Transfer.class);
        return query.getResultList();
    }
}
