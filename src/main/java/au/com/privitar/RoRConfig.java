package au.com.privitar;

import au.com.privitar.config.Calculations;
import io.dropwizard.Configuration;

import javax.validation.Valid;

/**
 *
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRConfig extends Configuration {

    @Valid
    private Calculations calculations;

    public Calculations getCalculations() {
        return calculations;
    }

    public void setCalculations(Calculations calculations) {
        this.calculations = calculations;
    }
}
