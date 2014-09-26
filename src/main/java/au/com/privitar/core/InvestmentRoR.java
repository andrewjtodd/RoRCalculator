package au.com.privitar.core;

import org.joda.time.DateTime;

/**
 * Created by Andrew Todd on 26/09/2014.
 */
public class InvestmentRoR implements RoRTarget {
    @Override
    public double getOpeningBalance(DateTime transactionDate) {
        return 0;
    }

    @Override
    public double getClosingBalance(DateTime transactionDate) {
        return 0;
    }

    @Override
    public int getDaysHeld() {
        return 0;
    }

    @Override
    public int getDaysInPeriod(DateTime startDate, DateTime endDate) {
        return 0;
    }

    @Override
    public double getRedemptions() {
        return 0;
    }

    @Override
    public double getPurchases() {
        return 0;
    }
}
