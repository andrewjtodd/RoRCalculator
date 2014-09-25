package au.com.privitar.core;

import au.com.privitar.RoRConfig;

/**
 * Created by Andrew Todd on 29/08/2014.
 */
public class RoRAnswer {
    private RoRConfig config;

    public RoRAnswer(RoRConfig config) {
        this.config = config;
    }

    public double calculate(double price, double units) {

        System.out.println("calculating the answer with base price of: " + config.getCalculations().getKwHCost());

        double answer = price * units;

        System.out.println("answer is: " + answer);

        return answer;
    }
}
