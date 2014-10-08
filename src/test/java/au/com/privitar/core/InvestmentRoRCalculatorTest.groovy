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
    private static Days period = Days.days(100);

    @Test
    public void testNumeratorCalc() throws Exception {
        long numerator = new InvestmentRoRCalculator().calculateInvestmentNumerator(transactionList, period);
        assert(numerator == 15000000);
    }

    @Test
    public void testDenominatorCalc() throws Exception {
        long denom = new InvestmentRoRCalculator().calculateInvestmentDenominator(transactionList, period);
        assert(denom == 900000000);

    }


    void setUp() {
        super.setUp()
        final Transaction transaction = new Transaction();
        transaction.setInvestment("IFL");
        transaction.setTransactionDate(new DateTime().withDate(2014, 9, 21));
        transaction.setTransactionType("Buy - Switch");
        transaction.setPositiveEffect(true);
        transaction.setValue(500);

        final Transaction t1 = new Transaction();
        t1.setInvestment("IFL");
        t1.setTransactionDate(new DateTime().withDate(2014, 9, 21));
        t1.setTransactionType("Contribution");
        t1.setPositiveEffect(true);
        t1.setValue(2000);

        final Transaction t2 = new Transaction();
        t2.setInvestment("IFL");
        t2.setTransactionDate(new DateTime().withDate(2014, 9, 21));
        t2.setTransactionType("Sell - Switch");
        t2.setPositiveEffect(false);
        t2.setValue(1000);

        final Transaction t3 = new Transaction();
        t3.setInvestment("BHP");
        t3.setTransactionDate(new DateTime().withDate(2014, 9, 21));
        t3.setTransactionType("Sell - Switch");
        t3.setPositiveEffect(false);
        t3.setValue(50);

        final Transaction t4 = new Transaction();
        t4.setInvestment("BHP");
        t4.setTransactionDate(new DateTime().withDate(2014, 9, 21));
        t4.setTransactionType("Buy");
        t4.setPositiveEffect(true);
        t4.setValue(100);

        transactionList = new ArrayList<Transaction>(3);
        transactionList.add(transaction);
        transactionList.add(t1);
        transactionList.add(t2);
//        transactionList.add(transaction3);
//        transactionList.add(transaction4);
    }

    void tearDown() {
        transactionList.clear();
    }
}
