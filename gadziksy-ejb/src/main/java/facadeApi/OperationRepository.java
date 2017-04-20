package facadeApi;

import jpa.Operation;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 19.04.17.
 */
@Local
public interface OperationRepository {
    List<Operation> getAllOperations(Long id);
    Optional<Operation> getLastOperation(String email);
}
