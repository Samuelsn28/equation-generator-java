package types.polinomial;

import types.NumberWriting;
import types.Valoravel;

import java.math.BigDecimal;
import java.util.List;

public class Polinomio implements NumberWriting {
    private final List<Valoravel> parts;

    public Polinomio(List<Valoravel> parts){
        if (parts == null){
            throw new IllegalArgumentException("The parameter 'parts' can't be null.");
        }
        if (parts.isEmpty()){
            throw new IllegalArgumentException(
                    "The 'parts' parameter must have a minimum of 1 element");
        }

        this.parts = parts;
    }

    public List<Valoravel> getParts() {
        return parts;
    }

    @Override
    public String getStringVersion(boolean withFirstSubtractionSign) {
        StringBuilder theReturn = new StringBuilder();

        boolean firstStep = true;
        for (Valoravel v : parts){
            if (firstStep){
                theReturn.append(v.getStringVersion(false));
                firstStep = false;
            } else {
                if (v.isNegative()){
                    theReturn.append(" - ");
                } else {
                    theReturn.append(" + ");
                }
                theReturn.append(v.getStringVersion(false));
            }
        }
        return theReturn.toString();
    }

    @Override
    public String toString() {
        StringBuilder theReturn = new StringBuilder();
        boolean firstStep = true;
        for (Valoravel v : parts){
            if (v.getCoeficiente().compareTo(BigDecimal.ZERO) != 0) {
                if (firstStep) {
                    theReturn.append(v.getStringVersion(true));
                    firstStep = false;
                    continue;
                } else {
                    if (v.isNegative()) {
                        theReturn.append(" - ");
                    } else {
                        theReturn.append(" + ");
                    }
                }
                theReturn.append(v.getStringVersion(false));
            }
        }
        return theReturn.toString();
    }
}
