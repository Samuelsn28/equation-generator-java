package main.generator;

import main.manipulators.DistributiveManipulator;
import main.types.Valoravel;
import main.types.numeros.Numero;
import main.types.polinomial.Incognita;
import main.types.polinomial.Monomio;
import main.types.polinomial.Polinomio;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EquationGenerator {
    private final DistributiveManipulator manipulator = new DistributiveManipulator();
    private final List<BigInteger> roots = new ArrayList<>();
    private final List<BigInteger> allRootsRegister = new ArrayList<>();
    private final List<Polinomio> polinomios = new ArrayList<>();
    private BigInteger currentCoefficientA = BigInteger.ONE;

    public Equation generateEquation(BigInteger degree,
                                     String variableSign,
                                     RootInterval rootInterval,
                                     CoefficientAInterval coefficientAInterval,
                                     boolean repeatedRoots
    ) {
        verifyExceptions(degree, variableSign, repeatedRoots, rootInterval);
        cleanLists();

        BigInteger i = BigInteger.ZERO;
        while (i.compareTo(degree) < 0) {
            generateRoot(rootInterval, repeatedRoots);
            i = i.add(BigInteger.ONE);
        }
        createPolinomios(variableSign, coefficientAInterval);
        return createEquation(degree);
    }

    private void verifyExceptions(BigInteger degree,
                                  String variableSign,
                                  boolean repeatedRoots,
                                  RootInterval rootInterval
    ){
        if (variableSign == null){
            throw new IllegalArgumentException("Variable sign can't be null");
        }
        if (variableSign.isEmpty()){
            throw new IllegalArgumentException("Variable sign can't be empty");
        }
        if (variableSign.length() > 1) {
            throw new IllegalArgumentException("Variable sign size can't be greater than 1");
        }
        if (!repeatedRoots) {
            BigInteger copyMinValueRoot = rootInterval.getMinValue().add(BigInteger.ZERO);
            long q = 1;
            while (!copyMinValueRoot.equals(rootInterval.getMaxValue())) {
                copyMinValueRoot = copyMinValueRoot.add(BigInteger.ONE);
                if (copyMinValueRoot.compareTo(BigInteger.ZERO) == 0){
                    continue;
                }
                q++;
            }
            if (q < degree.longValue()) {
                throw new IllegalArgumentException("With this minimum and maximum value it is not possible to create a " + degree + "º equation");
            }
        }
    }

    private void cleanLists(){
        roots.clear();
        allRootsRegister.clear();
        polinomios.clear();
    }

    private void generateRoot(RootInterval rootInterval,
                              boolean repeatedRoots
    ) {
        Random randomNumber = new Random();
        long longRoot;

        BigInteger minValueRoot = rootInterval.getMinValue();
        BigInteger maxValueRoot = rootInterval.getMaxValue();

        while (true) {
            if (minValueRoot.compareTo(minValueRoot.abs()) == 0) { // If min. value is positive
                long longMinValueRoot = minValueRoot.longValue();
                long newMaxValueRoot = maxValueRoot.longValue() - longMinValueRoot;
                if (minValueRoot.compareTo(BigInteger.ZERO) != 0) {
                    longRoot = randomNumber.nextLong((newMaxValueRoot + 1)) + longMinValueRoot;
                } else {
                    longRoot = randomNumber.nextLong((newMaxValueRoot)) + 1;
                }
            } else { // Min. value is negative
                long longMinValueRoot = minValueRoot.longValue();
                long newMaxValueRoot = maxValueRoot.longValue() + Math.abs(longMinValueRoot);

                do {
                    if (maxValueRoot.compareTo(BigInteger.ZERO) != 0) {
                        longRoot = randomNumber.nextLong((newMaxValueRoot + 1)) + longMinValueRoot;
                    } else {
                        longRoot = randomNumber.nextLong(newMaxValueRoot) + longMinValueRoot;
                    }
                } while (longRoot == 0);
            }
            BigInteger root = new BigInteger(String.valueOf(longRoot));

            List<Long> longRoots = new ArrayList<>();
            for (BigInteger b : roots) {
                longRoots.add( b.longValue() );
            }

            if (!repeatedRoots) {
                if (!longRoots.contains( root.longValue() )){
                    roots.add(root);
                    allRootsRegister.add(root);
                    break;
                }
            } else {
                if (!longRoots.contains( root.longValue() )){
                    roots.add(root);
                }
                allRootsRegister.add(root);
                break;
            }
        }
    }

    private void createPolinomios(String variableSign,
                                  CoefficientAInterval coefficientAInterval
    ){
        for (BigInteger r : allRootsRegister) {
            List<Incognita> incognitas = new ArrayList<>();
            incognitas.add(new Incognita(variableSign, BigDecimal.ONE));

            BigInteger coefficient = chooseCoefficient(coefficientAInterval);

            Monomio monomio = new Monomio(new BigDecimal(coefficient), incognitas);
            Numero root = new Numero( (r.multiply(coefficient).multiply(new BigInteger("-1"))) );

            List<Valoravel> valoravels = new ArrayList<>();
            valoravels.add(monomio);
            valoravels.add(root);

            polinomios.add(new Polinomio(valoravels));
        }
    }

    private BigInteger chooseCoefficient(CoefficientAInterval coefficientAInterval) {
        BigInteger chosenCoefficient;
        BigInteger newCurrentCoefficientA;
        do {
            if (coefficientAInterval.isAllRandomCoefficientRequested()) {
                return BigInteger.ONE;
            }
            chosenCoefficient = coefficientAInterval.chooseARandomCoeffient();
            newCurrentCoefficientA = currentCoefficientA.multiply(chosenCoefficient);

        } while (newCurrentCoefficientA.compareTo(coefficientAInterval.getMaxValue()) > 0
                || newCurrentCoefficientA.compareTo(coefficientAInterval.getMinValue()) < 0);

        currentCoefficientA = currentCoefficientA.multiply(chosenCoefficient);
        return chosenCoefficient;
    }

    private Equation createEquation(BigInteger degree) {
        Polinomio polinomioE = manipulator.aplicaDistributiva(polinomios);
        List<BigDecimal> rootsInDecimal = new ArrayList<>();

        for (BigInteger r : roots){
            String rString = r.toString();
            rootsInDecimal.add(new BigDecimal(rString));
        }
        polinomioE = addSimilarParts(polinomioE, degree);
        return new Equation(polinomioE, rootsInDecimal, BigDecimal.ZERO);
    }

    private Polinomio addSimilarParts(Polinomio polinomio, BigInteger degree){
        List<Valoravel> partsToNewPolinomio = new ArrayList<>();
        List<Valoravel> partsOfPolinomio = polinomio.getParts();
        BigDecimal copiedDegree = new BigDecimal(degree);

        while (copiedDegree.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal finalCopiedDegree = copiedDegree;
            List<Valoravel> requestedParties = new ArrayList<>(partsOfPolinomio.stream()
                    .filter(valoravel -> {
                        List<Incognita> incognitas = valoravel.getIncognita();
                        if (incognitas != null && !incognitas.isEmpty()) {
                            Incognita incognita = incognitas.get(0);
                            BigDecimal degreeOfPart = incognita.getExponent();
                            return degreeOfPart.compareTo(finalCopiedDegree) == 0;
                        }
                        return false;
                    })
                    .toList());

            if (!requestedParties.isEmpty()) {
                Valoravel v = requestedParties.get(0);
                requestedParties.remove(0);

                requestedParties.forEach(v::add);

                partsToNewPolinomio.add(v);
            }
            copiedDegree = copiedDegree.subtract(BigDecimal.ONE);
        }

        List<Valoravel> numParts = new ArrayList<>(partsOfPolinomio.stream()
                .filter(valoravel -> valoravel instanceof Numero)
                .toList()
        );
        if (!numParts.isEmpty()){
            Numero numero = (Numero) numParts.get(0);
            numParts.remove(0);
            numParts.forEach(numero::add);
            partsToNewPolinomio.add(numero);
        }
        return new Polinomio(partsToNewPolinomio);
    }
}
