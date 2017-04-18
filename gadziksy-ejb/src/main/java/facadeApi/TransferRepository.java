package facadeApi;

import jpa.Transfer;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 18.04.17.
 */
@Local
public interface TransferRepository {

    void writeTransfer(Transfer transfer);
    Optional<Transfer> getTransferById(Long id);
    List<Transfer> getAllTransfers();
}
