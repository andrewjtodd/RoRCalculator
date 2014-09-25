package au.com.privitar.config;

import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRConfig {

    @NotNull
    private double kwHCost;

    public double getKwHCost() {
        return kwHCost;
    }

    public void setKwHCost(double kwHCost) {
        this.kwHCost = kwHCost;
    }
}
