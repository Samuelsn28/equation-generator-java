import generator.Equation;
import generator.EquationGerator;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        EquationGerator gerator = new EquationGerator();
        Equation equation = gerator.generateEquation(
                new BigInteger("2"),
                "y",
                new BigInteger("-2"),
                new BigInteger("3"),
                true);
        System.out.println(equation);
        System.out.println(equation.getRoots());
    }
}