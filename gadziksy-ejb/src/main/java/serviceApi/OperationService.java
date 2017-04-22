package serviceApi;

import jpa.Operation;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 21.04.17.
 */
@Local
public interface OperationService {

    BigDecimal countAverage(List<Operation> operations);
    BigDecimal countMedian(List<Operation> operations);
    Optional<BigDecimal> countMode(List<Operation> operations);
}
