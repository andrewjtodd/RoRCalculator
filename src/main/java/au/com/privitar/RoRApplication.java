package au.com.privitar;

import au.com.privitar.health.RoRHealthCheck;
import au.com.privitar.resources.RoRResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRApplication extends Application<RoRConfig> {
    private final Logger logger = LoggerFactory.getLogger(RoRApplication.class);

    public static void main(String[] args) throws Exception {
        new RoRApplication().run(args);
    }

    @Override
    public String getName() {
        return "Rate of Return Calculator";
    }

    @Override
    public void initialize(Bootstrap<RoRConfig> bootstrap) {
    }

    @Override
    public void run(RoRConfig conf, Environment env) throws Exception {
        logger.info("Running DropWizard!!");

        RoRResource resource = new RoRResource(conf);

        final RoRHealthCheck healthCheck = new RoRHealthCheck();

        env.healthChecks().register("Rate of Return Calculator", healthCheck);
        env.jersey().register(resource);
    }
}
