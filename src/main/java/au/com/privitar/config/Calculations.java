package au.com.privitar.config;

import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class Calculations {

    @NotNull
    private double kwHCost;

    private DateTime endDate;

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    private DateTime startDate;


    public double getKwHCost() {
        return kwHCost;
    }

    public void setKwHCost(double kwHCost) {
        this.kwHCost = kwHCost;
    }
}
