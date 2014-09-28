package au.com.privitar.core;

import au.com.privitar.RoRConfig;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRAnswer {
    private RoRConfig config;
    private static final Logger logger = LoggerFactory.getLogger(RoRAnswer.class);

    public RoRAnswer(RoRConfig config) {
        this.config = config;
    }

    public double calculate(int memberId) {

        logger.debug("calculating the rate of return for member: " + memberId);

        double answer = 1 + 2;

        ClientRoR clientRoR = new ClientRoR();
        double openingBalance = clientRoR.getOpeningBalance(new DateTime());
        logger.debug("Client opening balance is: " + openingBalance);
        setup(memberId);
        preparePersonalCalculationData();
        prepareInvestmentCalculationData();


        logger.debug("answer is: " + answer);

        return answer;
    }

    /* This sets everything for the calculations. Includes any shared items. */
    private void setup(int memberId) {

        logger.debug("Running calculation for member: " + memberId);


    }

    /* This is the logic to prepare the data for personal calculation */
    private void preparePersonalCalculationData() {

    }

    /* This is the logic required to both prepare the data and do the calculation. */
    private void prepareInvestmentCalculationData() {
        
    }
}
