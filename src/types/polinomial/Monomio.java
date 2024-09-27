package types.polinomial;

import types.Valoravel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Monomio implements Valoravel {
    private BigDecimal coefficient = new BigDecimal("1");
    private boolean isNegative = false;
    private final List<Incognita> incognitas;

    public Monomio(BigDecimal coefficient, List<Incognita> incognitas){
        if (incognitas == null){
            throw new IllegalArgumentException("The parameter 'incognitas' can't be null.");
        }
        if (incognitas.isEmpty()){
            throw new IllegalArgumentException("The parameter 'incognitas' can't be empty.");
        }

        if (coefficient != null){
            this.coefficient = coefficient;

            if (!coefficient.equals(coefficient.abs())){
                isNegative = true;
            }
        }
        this.incognitas = incognitas;
    }

    @Override
    public String getStringVersion(boolean withFirstSubtractionSign) {
        String theReturn = toString();

        if (!withFirstSubtractionSign){
            return theReturn.replace("-", "");
        }
        return theReturn;
    }

    @Override
    public boolean isNegative() {
        return isNegative;
    }

    @Override
    public List<Incognita> getIncognita() {
        return incognitas;
    }

    @Override
    public BigDecimal getCoeficiente() {
        return coefficient;
    }

    @Override
    public void add(Valoravel v) {
        if (v instanceof Monomio){
            List<String> monIncognitasInStr = new ArrayList<>();
            for (Incognita im : v.getIncognita()){
                monIncognitasInStr.add(im.getSimbol());
            }

            List<String> thisIncognitasInStr = new ArrayList<>();
            for (Incognita it : incognitas){
                thisIncognitasInStr.add(it.getSimbol());
            }
            if ((new HashSet<>(thisIncognitasInStr).containsAll(monIncognitasInStr) &&
                    new HashSet<>(monIncognitasInStr).containsAll(thisIncognitasInStr))){
                coefficient = coefficient.add(v.getCoeficiente());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder theReturn = new StringBuilder();
        if (coefficient.compareTo(BigDecimal.ONE) != 0 &&
                coefficient.compareTo(new BigDecimal("-1")) != 0
                && coefficient.compareTo(BigDecimal.ZERO) != 0) {
            theReturn.append(coefficient);
        }
        if (coefficient.compareTo(new BigDecimal("-1")) == 0){
            theReturn.append("-");
        }

        if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
            for (Incognita i : incognitas) {
                theReturn.append(i.toString());
            }
        }
        return theReturn.toString();
    }
}
