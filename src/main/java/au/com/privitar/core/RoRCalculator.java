package au.com.privitar.core;

import java.math.BigDecimal;

/**
 * Created by Andrew Todd on 26/09/2014.
 */
public interface RoRCalculator {
    /**
     * Performs the calculation of the numerator.
     * @return BigDecimal
     */
    public BigDecimal calculateNumerator();

    /**
     * Performs the calculation of the denominator!
     * @return BigDecimal
     */
    public BigDecimal calculateDenominator();
}
