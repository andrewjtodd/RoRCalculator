package au.com.privitar.resources;

import au.com.privitar.RoRConfig;
import au.com.privitar.core.RoRAnswer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
@Path("/calculate")
@Produces(MediaType.APPLICATION_JSON)
public class RoRResource {
    private final RoRConfig config;

    public RoRResource(RoRConfig config) {
        this.config = config;
    }

    @GET
    public double getAnswer(@QueryParam("unitPrice") double price, @QueryParam("units") double units) {
        System.out.println("Unit Price: " + price);
        System.out.println("Units: " + units);
        RoRAnswer answer = new RoRAnswer(config);

        return answer.calculate(price, units);

    }
}
