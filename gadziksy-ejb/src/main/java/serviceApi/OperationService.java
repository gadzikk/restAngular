package serviceApi;

import jpa.Operation;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by gadzik on 21.04.17.
 */
@Local
public interface OperationService {

    BigDecimal countAverage(List<Operation> operations);
}
