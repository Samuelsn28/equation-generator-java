package manipulators;

import types.Valoravel;
import types.numeros.Numero;
import types.polinomial.Incognita;
import types.polinomial.Monomio;
import types.polinomial.Polinomio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DistributiveManipulator {
    private BigDecimal coeficienteP1;
    private List<Incognita> incognitasP1;
    private BigDecimal coeficienteP2;
    private List<Incognita> incognitasP2;

    public Polinomio aplicaDistributiva(List<Polinomio> polinomios){
        if (polinomios != null && !polinomios.isEmpty()) {
            Polinomio polinomioFinal = polinomios.get(0);

            for (int i = 1; i < polinomios.size(); i++) {
                Polinomio nextPolinomio = polinomios.get(i);
                polinomioFinal = multiplyPolinomios(nextPolinomio, polinomioFinal);
            }
            return polinomioFinal;
        }
        return null;
    }

    private Polinomio multiplyPolinomios(Polinomio polinomio1,
                                         Polinomio polinomio2){
        List<Valoravel> valoravelList = new ArrayList<>();

        for (Valoravel v1 : polinomio1.getParts()){
            completaDadosP1(v1);

            for (Valoravel v2 : polinomio2.getParts()){
                completaDadosP2(v2);

                BigDecimal coefficient =
                        coeficienteP1.multiply(coeficienteP2);

                if (incognitasP1 == null && incognitasP2 == null){
                    valoravelList.add( new Numero(coefficient) );
                    break;
                }
                ArrayList<Incognita> incognitasMonomio = new ArrayList<>();
                multiplicaIncognitas(incognitasMonomio);

                Monomio newMonomio = new Monomio(coefficient, incognitasMonomio);
                valoravelList.add(newMonomio);
            }
        }
        return new Polinomio(valoravelList);
    }

    private void completaDadosP1(Valoravel valoravel){
        coeficienteP1 = valoravel.getCoeficiente();

        if (valoravel.getIncognita() != null){
            incognitasP1 = valoravel.getIncognita();
        } else {
            incognitasP1 = null;
        }
    }

    private void completaDadosP2(Valoravel valoravel){
        coeficienteP2 = valoravel.getCoeficiente();

        if (valoravel.getIncognita() != null){
            incognitasP2 = valoravel.getIncognita();
        } else {
            incognitasP2 = null;
        }
    }

    private void multiplicaIncognitas(List<Incognita> incognitasMonomio) {
        if (incognitasP1 != null && incognitasP2 != null) {
            List<Incognita> copyIncognitasP2 = new ArrayList<>(incognitasP2);

            for (Incognita i1 : incognitasP1) {
                boolean adicionar = true;

                for (Incognita i2 : incognitasP2) {
                    if (i1.getSimbol().equals(i2.getSimbol())) {
                        Incognita newIncognita =
                                new Incognita(i1.getSimbol(), (i1.getExponent().add(i2.getExponent())));
                        incognitasMonomio.add(newIncognita);

                        copyIncognitasP2.remove(i2);

                        adicionar = false;
                        break;
                    }
                }
                if (adicionar) {
                    incognitasMonomio.add(i1);
                }
            }
            if (!copyIncognitasP2.isEmpty()){
                incognitasMonomio.addAll(copyIncognitasP2);
            }
        }
        if (incognitasP1 == null && incognitasP2 != null){
            incognitasMonomio.addAll(incognitasP2);
        }
        if (incognitasP1 != null && incognitasP2 == null){
            incognitasMonomio.addAll(incognitasP1);
        }
    }
}
