package generator;

import types.polinomial.Polinomio;

import java.math.BigDecimal;
import java.util.List;

public class Equation {
    private final Polinomio equationExpression;
    private final List<BigDecimal> roots;
    private final BigDecimal equal;

    public Equation(Polinomio equationExpression, List<BigDecimal> roots,
                    BigDecimal equal){
        this.equationExpression = equationExpression;
        this.roots = roots;
        this.equal = equal;
    }

    public Polinomio getEquationExpression() {
        return equationExpression;
    }

    public List<BigDecimal> getRoots() {
        return roots;
    }
    public BigDecimal isEqual() {
        return equal;
    }

    @Override
    public String toString() {
        return equationExpression.toString() + " = " + equal.toString();
    }
}
