package au.com.privitar;

import io.dropwizard.Configuration;

import javax.validation.Valid;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRConfig extends Configuration {

    @Valid
    private au.com.privitar.config.RoRConfig calculations;

    public au.com.privitar.config.RoRConfig getCalculations() {
        return calculations;
    }

    public void setCalculations(au.com.privitar.config.RoRConfig calculations) {
        this.calculations = calculations;
    }
}
