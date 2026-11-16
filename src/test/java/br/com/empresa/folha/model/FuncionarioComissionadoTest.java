package br.com.empresa.folha.model;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FuncionarioComissionadoTest {
    @Test void somaSalarioBaseEComissao() {
        var funcionario = new FuncionarioComissionado(3, "Caio", "935.411.347-80", new BigDecimal("2000"), new BigDecimal("10000"), new BigDecimal("5"));
        assertEquals(new BigDecimal("2500.00"), funcionario.calcularRemuneracao());
    }

    @Test void rejeitaPercentualAcimaDeCem() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioComissionado(3, "Caio", "93541134780", new BigDecimal("2000"), new BigDecimal("10000"), new BigDecimal("101")));
    }

    @Test void exibeCamposEspecificosNoDetalhe() {
        var funcionario = new FuncionarioComissionado(3, "Caio", "93541134780", new BigDecimal("2000"), new BigDecimal("10000"), new BigDecimal("5"));
        assertTrue(funcionario.toString().contains("Vendas: R$ 10000"));
    }
}
