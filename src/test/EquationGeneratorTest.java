package test;

import static org.junit.jupiter.api.Assertions.assertAll;

import main.generator.CoefficientAInterval;
import main.generator.RootInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.generator.EquationGenerator;
import main.generator.Equation;

import java.math.BigInteger;

public class EquationGeneratorTest {

    @Test
    @DisplayName("Creating Quadratic Equation")
    void creatingQuadraticEquation() {
        String equationSimbol = "x";
        BigInteger equationDegree = BigInteger.TWO;
        BigInteger firstRoot = BigInteger.ONE;
        BigInteger secondRoot = BigInteger.TWO;

        EquationGenerator eg = new EquationGenerator();
        Equation e = eg.generateEquation(
                equationDegree,
                equationSimbol,
                new RootInterval(
                        firstRoot,
                        secondRoot
                ),
                new CoefficientAInterval(
                        BigInteger.ONE,
                        BigInteger.ONE
                ),
                false
        );

        assertAll(
                // Assert if equation's simbol is correct.
                () -> Assertions.assertEquals(equationSimbol, e.getEquationExpression().getParts().get(0).getIncognita().get(0).getSimbol()),
                // Assert if equation's roots are correct.
                () -> Assertions.assertTrue(firstRoot.equals(e.getRoots().get(0).toBigInteger()) || firstRoot.equals(e.getRoots().get(1).toBigInteger())),
                // Assert if equation's degree is correct.
                () -> Assertions.assertEquals(equationDegree, e.getEquationExpression().getParts().get(0).getIncognita().get(0).getExponent().toBigInteger())
        );
    }

    @Test
    @DisplayName("Creating Cubic Equation")
    void creatingCubicEquation() {
        String equationSimbol = "y";
        BigInteger equationDegree = new BigInteger("3");
        BigInteger firstRoot = BigInteger.ONE;
        BigInteger secondRoot = BigInteger.TWO;
        BigInteger thirdRoot = new BigInteger("3");

        EquationGenerator eg = new EquationGenerator();
        Equation e = eg.generateEquation(
                equationDegree,
                equationSimbol,
                new RootInterval(
                        firstRoot,
                        thirdRoot
                ),
                new CoefficientAInterval(
                        BigInteger.ONE,
                        BigInteger.ONE
                ),
                false
        );

        assertAll(
                // Assert if equation's simbol is correct.
                () -> Assertions.assertEquals(equationSimbol, e.getEquationExpression().getParts().get(0).getIncognita().get(0).getSimbol()),
                // Assert if equation's roots are correct.
                () -> Assertions.assertTrue(firstRoot.equals(e.getRoots().get(0).toBigInteger()) || firstRoot.equals(e.getRoots().get(1).toBigInteger()) || firstRoot.equals(e.getRoots().get(2).toBigInteger())),
                // Assert if equation's degree is correct.
                () -> Assertions.assertEquals(equationDegree, e.getEquationExpression().getParts().get(0).getIncognita().get(0).getExponent().toBigInteger())
        );
    }
}
