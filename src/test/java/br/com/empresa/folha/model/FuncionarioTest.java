package br.com.empresa.folha.model;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FuncionarioTest {
    @Test void validaCpfEIdentificador() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(0, "Ana", "52998224725", BigDecimal.ZERO, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "Ana", "12345678900", BigDecimal.ZERO, BigDecimal.ZERO));
    }

    @Test void polimorfismoCalculaCadaCategoria() {
        Funcionario clt = new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("1000"), BigDecimal.ZERO);
        Funcionario pj = new FuncionarioPJ(2, "Beto", "11144477735", new BigDecimal("500"), 2);
        assertEquals(new BigDecimal("1000.00"), clt.calcularRemuneracao());
        assertEquals(new BigDecimal("1000.00"), pj.calcularRemuneracao());
    }
}
