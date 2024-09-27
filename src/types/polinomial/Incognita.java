package types.polinomial;

import util.NumberTransformerUtil;

import java.math.BigDecimal;

public class Incognita {
    private final String simbol;
    private final BigDecimal exponent;

    public Incognita(String simbol, BigDecimal exponent){
        if (simbol == null){
            throw new IllegalArgumentException("The parameter 'simbol' can't be null.");
        }
        if (simbol.isEmpty()){
            throw new IllegalArgumentException("The parameter 'simbol' can't be empty.");
        }

        if (exponent == null){
            throw new IllegalArgumentException("The parameter 'exponent' can't be null.");
        }

        this.simbol = simbol;
        this.exponent = exponent;
    }

    public String getSimbol() {
        return simbol;
    }

    public BigDecimal getExponent() {
        return exponent;
    }

    @Override
    public String toString() {
        String exponentString =
                NumberTransformerUtil.toExponentCharacters(exponent, true);
        return simbol + exponentString;
    }
}
