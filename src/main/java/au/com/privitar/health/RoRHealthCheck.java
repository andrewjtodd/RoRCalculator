package au.com.privitar.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRHealthCheck extends HealthCheck{
    @Override
    protected Result check() throws Exception {

        return HealthCheck.Result.healthy();
    }
}
