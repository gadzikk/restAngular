package service;

import jpa.Operation;
import serviceApi.OperationService;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by gadzik on 21.04.17.
 */
@Stateless
public class OperationServiceBean implements OperationService {

    @Override
    public BigDecimal countAverage(List<Operation> operations) {
        BigDecimal divider = new BigDecimal(operations.size() - 1);

        BigDecimal sum = operations.stream().map(x -> x.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(divider, 4, BigDecimal.ROUND_HALF_UP);
    }
}
