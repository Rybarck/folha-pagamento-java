package br.com.empresa.folha.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class IdentificavelTest {
    @Test void funcionarioRetornaCodigoPelaInterface() {
        Identificavel identificavel = new FuncionarioCLT(42, "Ana", "52998224725", BigDecimal.ZERO, BigDecimal.ZERO);
        assertEquals(42, identificavel.getIdentificador());
    }
}
