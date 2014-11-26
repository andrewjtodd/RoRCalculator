package au.com.privitar.core;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;

/**
 * Created by Andrew Todd on 3/10/2014.
 */
public class TransactionItem {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TransactionItem.class);
    private Transaction transaction;

    public TransactionItem(Transaction i) {
        super();
        this.transaction = i;
    }

    private int calculateDaysHeld() {
        DateTime trandate = transaction.getTransactionDate();

        // this works fine if the date is always today, what about back periods?
        DateTime now = new DateTime();

        int daysHeld = Days.daysBetween(trandate, now).getDays();

        logger.debug("Held this investment " + transaction.getInvestment() + " for " + daysHeld + " days.");

        return daysHeld;
    }

    public long getOutcome() {

        long outcome = calculateDaysHeld() * transaction.getValueAsLong();
        return outcome;
    }
}
