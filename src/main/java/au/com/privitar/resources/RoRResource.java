package au.com.privitar.resources;

import au.com.privitar.RoRConfig;
import au.com.privitar.core.RoRAnswer;
import au.com.privitar.core.Transaction;
import au.com.privitar.core.TransactionItem;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
@Path("/calculate")
@Produces(MediaType.APPLICATION_JSON)
public class RoRResource {
    private final RoRConfig config;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RoRResource.class);

    public RoRResource(RoRConfig config) {
        this.config = config;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void getAnswer(List<Transaction> transactions) {

        // we need to cut the transactions into a few items
        // 1. investments, and organised by in and out transactions
        // 2. as-is, but organised by in and out transactions
        // so we need to map the in and out transactions

        // get all the buy and sell transactions separated - true means positive balance impact
        List<Transaction> positiveTrans = groupTransactionsByEffect(transactions, true);
        List<Transaction> negativeTrans = groupTransactionsByEffect(transactions, false);

        // have enough now to do the calculations for the account


        // I don't think this is right. Just get the investments together, then filter later.
        // TODO: remove this specifically
        // group all investments that have a positive impact on balance
//        logger.debug("Getting all buys, based on investment");
//        Map<String, List<Transaction>> positiveInvestments = groupInvestments(transactions, true);
//
//        // group all investments that have negative impact on balance
//        logger.debug("Getting all sells, based on investment");
//        Map<String, List<Transaction>> negativeInvestments = groupInvestments(transactions, false);

        // Maybe this is the way:
        // todo - check and compare with above
        // this gives a bunch of investments, containing a list of transactions
        Map<String, List<Transaction>> groupedInvestments = groupInvestments(transactions);

        // process the investments - they need to be split and then the calculation occurs
        calculateInvestmentRoR(groupedInvestments);


    }

    private void calculateInvestmentRoR(Map<String, List<Transaction>> groupedInvestments) {
        // process each investment individually
        Collection<List<Transaction>> transactions = groupedInvestments.values();

        transactions.forEach((i) -> calculateInvestmentAnswer(i));

    }

    private void calculateInvestmentAnswer(List<Transaction> t) {

        // we have an investment so now run the actual workings of the calculation

        // split the investment into buy and sell groupings
        List<Transaction> positiveTrans = groupTransactionsByEffect(t, true);
        List<Transaction> negativeTrans = groupTransactionsByEffect(t, false);

        printTransactions(positiveTrans);
        printTransactions(negativeTrans);

        // calculate the numerator

        // calculate the denominator
        long den = calculateInvestmentDenominator(positiveTrans, negativeTrans);
        long num = calculateInvestmentNumerator(positiveTrans, negativeTrans);

//        t.stream().forEach(tran -> calculateInvestmentRoR(tran));

    }

    private void printTransactions(List<Transaction> t) {

        logger.debug("==============================================");
        t.stream().forEach((i) -> logger.debug(i.toString()));
        logger.debug("==============================================");

    }

    private long calculateInvestmentNumerator(List<Transaction> pos, List<Transaction> neg) {
        logger.debug("Calculating the investment numerators");

        return 0;
    }

    private long calculateInvestmentDenominator(List<Transaction> pos, List<Transaction> neg) {
        logger.debug("Calculating the investment denominators");

        //
        pos.stream().forEach((i) -> prepBaseData(i));
        return 0;
    }

    private void prepBaseData(Transaction i) {
        // this is not the way to do it. should just get the transaction item to do all the work it needs.
        TransactionItem item = new TransactionItem(i);
        item.prepareSelf();
    }

    private Map<String, List<Transaction>> groupInvestments(List<Transaction> transactions) {
        Map<String, List<Transaction>> theList = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getInvestment));

        printInvestments(theList);
        return theList;
    }

    private Map<String, List<Transaction>> groupInvestments(List<Transaction> transactions, boolean b) {

        Map<String, List<Transaction>> theList = transactions.parallelStream()
                .filter(t -> t.isPositiveEffect() == b)
                .collect(Collectors.groupingBy(Transaction::getInvestment));

        printInvestments(theList);

        return theList;
    }

    private void printInvestments(Map<String, List<Transaction>> groupByTransaction) {
        logger.debug("-------------investments----------" + groupByTransaction.size() + " investment(s)");
        logger.debug(Arrays.toString(groupByTransaction.entrySet().toArray()));
        logger.debug("-------------end---------------");
    }


    private List<Transaction> groupTransactionsByEffect(List<Transaction> transactions, boolean positiveEffect) {

        return transactions.parallelStream()
                .filter(transaction -> transaction.isPositiveEffect() == positiveEffect)
                .collect(Collectors.toList());

    }

//    private void printTransactionsList(List<Transaction> trans) {
//        logger.debug("-------------------------" + trans.size() + " in total");
//
//        trans.stream().forEach((item) -> logger.debug(item.toString()));
//
//        logger.debug("-----------end--------------");
//    }

    @GET
    public double getAnswer(@QueryParam("member") String member) {
        RoRAnswer answer = new RoRAnswer(config);

        // todo
        return answer.calculate(1);

    }
}
