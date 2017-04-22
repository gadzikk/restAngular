package service;

import jpa.Operation;
import serviceApi.OperationService;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public BigDecimal countMedian(List<Operation> operations) {
        List<BigDecimal> operationAmounts = operations.stream().map(x -> x.getAmount()).collect(Collectors.toList());
        operationAmounts.sort(Comparator.naturalOrder());

        int mediumOfElements = operationAmounts.size() / 2;
        BigDecimal median;

        if (operationAmounts.size() % 2 != 0) {
            median = operationAmounts.get(mediumOfElements);
        } else {
            median = operationAmounts.get(mediumOfElements - 1).add(operationAmounts.get(mediumOfElements))
                    .divide(new BigDecimal(2), 4, BigDecimal.ROUND_HALF_UP);
        }
        return median;
    }

    @Override
    public Optional<BigDecimal> countMode(List<Operation> operations) {
        Map<BigDecimal, Long> countsOfElements = new HashMap<>();

        Set<BigDecimal> uniqueAmounts = operations.stream().map(x -> x.getAmount()).collect(Collectors.toSet());

        uniqueAmounts.forEach(amount -> {
            countsOfElements.put(amount, operations.stream().map(o -> o.getAmount())
                    .filter(o -> o.compareTo(amount) == 0)
                    .count());
        });
        Long max = countsOfElements.values().stream().max(Long::compareTo).get();

        uniqueAmounts = countsOfElements.entrySet().stream()
                .filter(x -> max.equals(x.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (uniqueAmounts.size() != 1) {
            return Optional.empty();
        }
        return uniqueAmounts.stream().findFirst();
    }
}
