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
        this.transaction = i;
    }

    public void prepareSelf() {
        int daysHeld = calculateDaysHeld();


    }

    private int calculateDaysHeld() {
        DateTime trandate = transaction.getTransactionDate();
        DateTime now = new DateTime();

        int daysHeld = Days.daysBetween(trandate, now).getDays();

        logger.debug("Held this investment " + transaction.getInvestment() + " for " + daysHeld + " days.");

        return daysHeld;
    }
}
