package au.com.privitar.resources;

import au.com.privitar.RoRConfig;
import au.com.privitar.core.RoRAnswer;
import au.com.privitar.core.Transaction;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
@Path("/calculate")
@Produces(MediaType.APPLICATION_JSON)
public class RoRResource {
    private final RoRConfig config;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RoRResource.class);

    public RoRResource(RoRConfig config) {
        this.config = config;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void getAnswer(List<Transaction> transactions) {
        logger.debug("Got the list of transactions from the json file, it is: ");

        transactions.stream().forEach((transaction) -> {logger.debug(transaction.toString());});

        // we need to cut the transactions into a few items
        // 1. investments, and organised by in and out transactions
        // 2. as-is, but organised by in and out transactions
        // so we need to map the in and out transactions


    }

    @GET
    public double getAnswer(@QueryParam("member") String member) {
        RoRAnswer answer = new RoRAnswer(config);

        // todo
        return answer.calculate(1);

    }
}
