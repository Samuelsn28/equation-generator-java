package types.numeros;

import types.Valoravel;
import types.polinomial.Incognita;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Numero implements Valoravel {
    private BigDecimal valor;

    public Numero(BigDecimal valor){
        if (valor == null){
            throw new IllegalArgumentException("The parameter 'valor' can't be null.");
        }
        this.valor = valor;
    }

    public Numero(BigInteger valor){
        if (valor == null){
            throw new IllegalArgumentException("The parameter 'valor' can't be null.");
        }
        this.valor = new BigDecimal(valor.toString());
    }

    @Override
    public String getStringVersion(boolean withFirstSubtractionSign) {
        if (!withFirstSubtractionSign){
            return valor.abs().toString();
        }
        return valor.toString();
    }

    @Override
    public boolean isNegative() {
        return valor.compareTo( (valor.abs()) ) < 0;
    }

    @Override
    public List<Incognita> getIncognita() {
        return null;
    }

    @Override
    public BigDecimal getCoeficiente() {
        return valor;
    }

    @Override
    public void add(Valoravel v) {
        if (v instanceof Numero){
            valor = valor.add(v.getCoeficiente());
        }
    }


    @Override
    public String toString() {
        return valor.toString();
    }
}
