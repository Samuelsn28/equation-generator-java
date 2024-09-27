package types;

import types.polinomial.Incognita;

import java.math.BigDecimal;
import java.util.List;

public interface Valoravel extends NumberWriting {
    boolean isNegative();
    List<Incognita> getIncognita();
    BigDecimal getCoeficiente();
    void add(Valoravel v);
}
