package au.com.privitar.core;

import org.joda.time.DateTime;

/**
 * Created by Andrew Todd on 26/09/2014.
 */
public interface RoRTarget {
    /**
     * Determines the value of the opening balance in cents, as at the transaction date.
     * @param transactionDate
     * @return double
     */
    public double getOpeningBalance(DateTime transactionDate);

    /**
     * Determines the value of the closing balance in cents, as at the transaction date.
     * @param transactionDate
     * @return double
     */
    public double getClosingBalance(DateTime transactionDate);

    /**
     * Determines how long the target 'held' the 'transaction', in days. Based on the transaction date.
     * @return int
     */
    public int getDaysHeld();

    /**
     * Determines the number of days in the reporting period. Based on the days submitted.
     * @param startDate
     * @param endDate
     * @return int
     */
    public int getDaysInPeriod(DateTime startDate, DateTime endDate);

    /**
     * Determines all the redemptions for the target in cents.
     * @return double
     */
    public double getRedemptions();

    /**
     * Determines all the purchases for the target in cents.
     * @return double
     */
    public double getPurchases();
}
