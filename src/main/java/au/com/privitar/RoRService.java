package au.com.privitar;

import au.com.privitar.health.RoRHealthCheck;
import au.com.privitar.resources.RoRResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRService extends Application<RoRConfig> {

    public static void main(String[] args) throws Exception {
        new RoRService().run(args);
    }

    @Override
    public String getName() {
        return "Savings Calculator";
    }

    @Override
    public void initialize(Bootstrap<RoRConfig> bootstrap) {

    }

    @Override
    public void run(RoRConfig conf, Environment env) throws Exception {

        RoRResource resource = new RoRResource(conf);

        final RoRHealthCheck healthCheck = new RoRHealthCheck();

        env.healthChecks().register("Savings Calculator", healthCheck);
        env.jersey().register(resource);
    }
}
