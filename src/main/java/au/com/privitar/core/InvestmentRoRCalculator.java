package au.com.privitar.core;

import org.joda.time.DateTime;
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

    // Currently assumes the reporting period has been determined, and the end date is 'now'.

    public double calculateRoR(List<Transaction> tlist, DateTime periodStart, DateTime periodEnd) {
        logger.debug("+++++++---> Calculating RoR for: " + tlist.get(0).getInvestment() + "");

        double numerator = calculateInvestmentNumerator(tlist, periodStart, periodEnd);

        double denominator = calculateInvestmentDenominator(tlist, periodStart, periodEnd);

        double answer = numerator / denominator;
        logger.debug("+++++--> answer: " + answer);

        return answer;
    }

    private Days getReportingPeriod(DateTime periodStart, DateTime periodEnd) {
        Days d = Days.daysBetween(periodStart, periodEnd);
        logger.debug("Reporting period; " + d.getDays());
        return d;
    }

    private void printTransactions(List<Transaction> t) {

        logger.debug("Transaction is: ");
        t.stream().forEach((i) -> logger.debug(i.toString()));

    }

    private double calculateInvestmentNumerator(List<Transaction> tlist, DateTime periodStart, DateTime periodEnd) {
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

        long openingBalance = 229000;

        // How do we get the opening balance in here?
        // getOpeningBalance();

        long closingBalance = positiveSum - negativeSum;
        logger.debug("Closing balance is: " + closingBalance);

        long numerator = (closingBalance - openingBalance + positiveSum - negativeSum) * getReportingPeriod(periodStart, periodEnd).getDays();
        logger.debug("Numerator is: " + numerator);
        return numerator;
    }

    private long calculateInvestmentDenominator(List<Transaction> tlist, DateTime periodStart, DateTime periodEnd) {
        long openingBalance = 9000000;

        // this is what needs to be fixed, when it gets specified properly
        long answer = doTempCalcDependingOnFixingTheFormula(tlist);

        answer = answer + openingBalance * getReportingPeriod(periodStart, periodEnd).getDays();
        logger.debug("Denominator is: " + answer);

        return answer;
    }


    private long doTempCalcDependingOnFixingTheFormula(List<Transaction> tlist) {

        List<Transaction> list = tlist.stream()
                .filter(tran -> tran.isPositiveEffect())
                .collect(Collectors.toList());

        long answer = list.stream()
                .map((i) -> getTransactionOutcome(i))
                .reduce(0L, (accumulator, item) -> accumulator + item);

        return answer;
    }

    private long getTransactionOutcome(Transaction i) {
        return new TransactionItem(i).getOutcome();
    }
}
