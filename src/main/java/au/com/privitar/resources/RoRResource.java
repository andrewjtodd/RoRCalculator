package au.com.privitar.resources;

import au.com.privitar.RoRConfig;
import au.com.privitar.core.InvestmentRoRCalculator;
import au.com.privitar.core.RoRAnswer;
import au.com.privitar.core.Transaction;
import org.joda.time.DateTime;
import org.joda.time.Days;
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

        Days reportingPeriod = determineReportingPeriod();

        Map<String, List<Transaction>> groupedInvestments = groupInvestments(transactions);

        // process the investments - they need to be split and then the calculation occurs
        calculateInvestmentGroupRoR(groupedInvestments, reportingPeriod);
    }

    private Days determineReportingPeriod() {
        DateTime start = config.getCalculations().getStartDate();
        DateTime end = config.getCalculations().getEndDate();

        Days days = Days.daysBetween(start, end);
        logger.debug("Reporting period is: " + days.getDays() + " days.");
        return days;
    }

    private void calculateInvestmentGroupRoR(Map<String, List<Transaction>> groupedInvestments, Days period) {
        // process each investment individually
        Collection<List<Transaction>> transactions = groupedInvestments.values();

        transactions.forEach((investment) -> calculateInvestmentRoR(investment, period));
    }

    public double calculateInvestmentRoR(List<Transaction> investmentTransactions, Days reportingPeriod) {
        double ror = 0;

        InvestmentRoRCalculator calculator = new InvestmentRoRCalculator();
        ror = calculator.calculateRoR(investmentTransactions, reportingPeriod);

        // finally, convert the rate of return into a percentage.
        return ror;
    }

    private Map<String, List<Transaction>> groupInvestments(List<Transaction> transactions) {
        Map<String, List<Transaction>> theList = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getInvestment));

        return theList;
    }

    private Map<String, List<Transaction>> groupInvestments(List<Transaction> transactions, boolean b) {

        Map<String, List<Transaction>> theList = transactions.parallelStream()
                .filter(t -> t.isPositiveEffect() == b)
                .collect(Collectors.groupingBy(Transaction::getInvestment));

        return theList;
    }

    private void printInvestments(Map<String, List<Transaction>> groupByTransaction) {
        logger.debug("Total investments to process: " + groupByTransaction.size());
        logger.debug("-------------investments----------" + groupByTransaction.size() + " investment(s)");
        logger.debug(Arrays.toString(groupByTransaction.keySet().toArray()));
        logger.debug("-------------end---------------");
    }

    @GET
    public double getAnswer(@QueryParam("member") String member) {
        RoRAnswer answer = new RoRAnswer(config);

        // todo
        return answer.calculate(1);

    }
}
