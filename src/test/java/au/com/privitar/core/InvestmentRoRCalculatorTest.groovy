package au.com.privitar.core

import com.fasterxml.jackson.databind.ObjectMapper
import io.dropwizard.jackson.Jackson
import org.joda.time.DateTime
import org.joda.time.Days
import org.junit.Test

/**
 * Created by Andrew Todd on 8/10/2014.
 */
class InvestmentRoRCalculatorTest extends GroovyTestCase {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static List<Transaction> transactionList;
    private static Days period = Days.days(458);
    private static DateTime start = new DateTime().withDate(2007, 7, 1);
    private static DateTime end = new DateTime().withDate(2008, 9, 30);


    @Test
    public void testNumeratorCalc() throws Exception {
        long numerator = new InvestmentRoRCalculator().calculateInvestmentNumerator(transactionList, start, end);
        assert(numerator == 15000000);
    }

    @Test
    public void testDenominatorCalc() throws Exception {
        long denom = new InvestmentRoRCalculator().calculateInvestmentDenominator(transactionList, start, end);
        assert(denom == 900000000);

    }

    @Test
    public void testCalc() throws Exception {
        long answer = new InvestmentRoRCalculator().calculateRoR(transactionList, start, end);
        assert (answer == 0.15387)

    }


    void setUp() {
        super.setUp()
        final Transaction transaction = new Transaction();
        transaction.setInvestment("IFL");
        transaction.setTransactionDate(new DateTime().withDate(2007, 7, 1));
        transaction.setTransactionType("Contribution");
        transaction.setPositiveEffect(true);
        transaction.setValue(90000);

        final Transaction t1 = new Transaction();
        t1.setInvestment("IFL");
        t1.setTransactionDate(new DateTime().withDate(2007, 7, 1));
        t1.setTransactionType("Buy");
        t1.setPositiveEffect(true);
        t1.setValue(500);

        final Transaction tran2 = new Transaction();
        tran2.setInvestment("IFL");
        tran2.setTransactionDate(new DateTime().withDate(2008, 1, 1));
        tran2.setTransactionType("Buy");
        tran2.setPositiveEffect(true);
        tran2.setValue(2000);

        final Transaction t3 = new Transaction();
        t3.setInvestment("IFL");
        t3.setTransactionDate(new DateTime().withDate(2007, 7, 1));
        t3.setTransactionType("Sell");
        t3.setPositiveEffect(false);
        t3.setValue(1000);

        final Transaction t4 = new Transaction();
        t4.setInvestment("IFL");
        t4.setTransactionDate(new DateTime().withDate(2009, 10, 1));
        t4.setTransactionType("Sell");
        t4.setPositiveEffect(false);
        t4.setValue(50);


        transactionList = new ArrayList<Transaction>(5);
        transactionList.add(transaction);
        transactionList.add(t1);
        transactionList.add(tran2);
        transactionList.add(t3);
        transactionList.add(t4);

    }

    void tearDown() {
        transactionList.clear();
    }
}
