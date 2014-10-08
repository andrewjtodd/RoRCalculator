package au.com.privitar.core;

import org.joda.time.Days;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrew Todd on 8/10/2014
 */
public class InvestmentRoRCalculator {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(InvestmentRoRCalculator.class);


    public InvestmentRoRCalculator() {
        super();
    }

    public double calculateRoR(List<Transaction> tlist, Days reportingPeriod) {
        logger.debug("+++++++---> Calculating RoR for: " + tlist.get(0).getInvestment() + "");

        double numerator = calculateInvestmentNumerator(tlist, reportingPeriod);

        double denominator = calculateInvestmentDenominator(tlist, reportingPeriod);

        return 0;
    }

    private void printTransactions(List<Transaction> t) {

        logger.debug("Transaction is: ");
        t.stream().forEach((i) -> logger.debug(i.toString()));

    }

//    private List<Transaction> groupTransactionsByEffect(List<Transaction> transactions, boolean positiveEffect) {
//
//        return transactions.parallelStream()
//                .filter(transaction -> transaction.isPositiveEffect() == positiveEffect)
//                .collect(Collectors.toList());
//
//    }

    private double calculateInvestmentNumerator(List<Transaction> tlist, Days reportingPeriod) {
        // this will be needed for the numerator
        long positiveSum = tlist.stream()
                .filter(tran -> tran.isPositiveEffect())
                .mapToLong(tran -> tran.getValueAsLong())
                .sum();

        logger.debug("Sum of positive transactions: " + positiveSum);

        long negativeSum = tlist.stream()
                .filter(tran -> !tran.isPositiveEffect())
                .mapToLong(tran -> tran.getValueAsLong())
                .sum();

        logger.debug("Sum of negative transactions: " + negativeSum);

        long openingBalance = 0;
        long closingBalance = 0;

        long numerator = (closingBalance - openingBalance + positiveSum - negativeSum) * reportingPeriod.getDays();
        logger.debug("Numerator is: " + numerator);
        return numerator;
    }

    private long calculateInvestmentDenominator(List<Transaction> tlist, Days reportingPeriod) {
        long openingBalance = 9000000;

        doTempCalcDependingOnFixingTheFormula(tlist, reportingPeriod);

        long denominator = openingBalance * reportingPeriod.getDays();
        logger.debug("Denominator is: " + denominator);
        return denominator;
    }

    private void doTempCalcDependingOnFixingTheFormula(List<Transaction> tlist, Days reportingPeriod) {
        List<Transaction> list = tlist.stream()
                .filter(tran -> tran.isPositiveEffect())
                .collect(Collectors.toList());

        long answer = list.stream()
                        .map((i) -> getTransactionOutcome(i))
                        .reduce(0L, (accumulator, item) -> accumulator + item);

        logger.debug("Answer: " + answer);
    }

    private long getTransactionOutcome(Transaction i) {
        return new TransactionItem(i).getOutcome();
    }
}
