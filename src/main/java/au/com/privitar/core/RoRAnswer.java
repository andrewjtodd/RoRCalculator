package au.com.privitar.core;

import au.com.privitar.RoRConfig;
import org.joda.time.DateTime;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRAnswer {
    private RoRConfig config;

    public RoRAnswer(RoRConfig config) {
        this.config = config;
    }

    public double calculate(int memberId) {

        System.out.println("calculating the rate of return for member: " + memberId);

        double answer = 1 + 2;

        ClientRoR clientRoR = new ClientRoR();
        double openingBalance = clientRoR.getOpeningBalance(new DateTime());
        System.out.println("Client opening balance is: " + openingBalance);
        setup(memberId);
        preparePersonalCalculationData();
        prepareInvestmentCalculationData();


        System.out.println("answer is: " + answer);

        return answer;
    }

    /* This sets everything for the calculations. Includes any shared items. */
    private void setup(int memberId) {

        System.out.println("Running calculation for member: " + memberId);


    }

    /* This is the logic to prepare the data for personal calculation */
    private void preparePersonalCalculationData() {

    }

    /* This is the logic required to both prepare the data and do the calculation. */
    private void prepareInvestmentCalculationData() {
        
    }
}
