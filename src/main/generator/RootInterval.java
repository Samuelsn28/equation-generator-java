package main.generator;

import java.math.BigInteger;

public class RootInterval {
    private final BigInteger minValue;
    private final BigInteger maxValue;

    public RootInterval(BigInteger minValue, BigInteger maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;

        verifyExceptions();
    }

    private void verifyExceptions(){
        if (minValue.compareTo(maxValue) > 0){
            throw new IllegalArgumentException("Min value of root can't greater than max value of root");
        }
    }

    public BigInteger getMinValue() {
        return minValue;
    }

    public BigInteger getMaxValue() {
        return maxValue;
    }
}
