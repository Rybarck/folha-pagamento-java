package br.com.empresa.folha.model;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TributavelTest {
    @Test void rejeitaAliquotaInvalida() {
        Tributavel tributavel = new FuncionarioCLT(1, "Ana", "52998224725", BigDecimal.ONE, BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> tributavel.calcularTributos(new BigDecimal("101")));
    }
}
