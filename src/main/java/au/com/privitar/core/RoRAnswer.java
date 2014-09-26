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

    public double calculate(int memberId) {

        System.out.println("calculating the rate of return for member: " + memberId);

        double answer = 1 + 2;

        System.out.println("answer is: " + answer);

        return answer;
    }
}
