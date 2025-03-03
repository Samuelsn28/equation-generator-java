package main.generator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoefficientAInterval {
    private final BigInteger minValue;
    private final BigInteger maxValue;
    private final List<BigInteger> allValues = new ArrayList<>();
    private final List<BigInteger> allValuesCount = new ArrayList<>();
    public final boolean minAndMaxValuesAreGreaterThanZero;
    public final boolean minAndMaxValuesAreLessThanZero;
    public final boolean minAndMaxValuesAreGreaterOrLessThanZero;

    public CoefficientAInterval(BigInteger minValue,
                                BigInteger maxValue
    ) {
        this.minValue = minValue;
        this.maxValue = maxValue;

        verifyExceptions();

        minAndMaxValuesAreGreaterThanZero =
                (minValue.compareTo(BigInteger.ZERO) > 0)
                        && (maxValue.compareTo(BigInteger.ZERO) > 0);
        minAndMaxValuesAreLessThanZero =
                (minValue.compareTo(BigInteger.ZERO) < 0)
                        && (maxValue.compareTo(BigInteger.ZERO) < 0);
        minAndMaxValuesAreGreaterOrLessThanZero =
                minAndMaxValuesAreGreaterThanZero || minAndMaxValuesAreLessThanZero;

        BigInteger minValueCopy = minValue;
        while (minValueCopy.compareTo(maxValue) <= 0) {
            if (minValueCopy.compareTo(BigInteger.ZERO) != 0) {
                allValues.add(minValueCopy);
                allValuesCount.add(minValueCopy);
            }
            minValueCopy = minValueCopy.add(BigInteger.ONE);
        }
    }

    private void verifyExceptions(){
        if (minValue.compareTo(maxValue) > 0){
            throw new IllegalArgumentException("Min value of coefficient A can't greater than max value of the coefficient.");
        }
        if (minValue.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException("Min value of coefficient A can't be equal zero.");
        }
        if (maxValue.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException("Max value of coefficient A can't be equal zero.");
        }
    }

    public BigInteger getMinValue() {
        return minValue;
    }

    public BigInteger getMaxValue() {
        return maxValue;
    }

    public BigInteger chooseARandomCoeffient() {
        Random random = new Random();

        BigInteger chosenCoefficient = allValues.get(random.nextInt( allValues.size() ));
        allValuesCount.remove(chosenCoefficient);

        return chosenCoefficient;
    }

    public boolean isAllRandomCoefficientRequested() {
        return allValuesCount.isEmpty();
    }
}
