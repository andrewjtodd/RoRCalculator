package au.com.privitar.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Todd on 26/09/2014.
 */
public class Transaction {
    @NotNull
    @JsonProperty
    private DateTime transactionDate;

    @NotNull
    @JsonProperty
    private String transactionType;

    @NotNull
    @JsonProperty
    private double value;

    @NotNull
    @JsonProperty
    private String investment;

    public boolean isPositiveEffect() {
        return positiveEffect;
    }

    public void setPositiveEffect(boolean isPositiveEffect) {
        this.positiveEffect = isPositiveEffect;
    }

    @NotNull
    @JsonProperty
    private boolean positiveEffect;

    public DateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(DateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    @Override
    public String toString() {
        return String.format("Transaction{investment = '%s', value='%f', type='%s', date='%s', effect='%b'}",
                investment, value, transactionType, transactionDate, positiveEffect);
    }
}
