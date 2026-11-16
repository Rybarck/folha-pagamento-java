package br.com.empresa.folha.model;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FuncionarioCLTTest {
    @Test void calculaSalarioBaseMaisBeneficios() {
        var funcionario = new FuncionarioCLT(1, "Ana", "529.982.247-25", new BigDecimal("3000"), new BigDecimal("500"));
        assertEquals(new BigDecimal("3500.00"), funcionario.calcularRemuneracao());
    }

    @Test void rejeitaBeneficioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("3000"), new BigDecimal("-1")));
    }

    @Test void calculaTributoSobreRemuneracao() {
        var funcionario = new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("3000"), new BigDecimal("500"));
        assertEquals(new BigDecimal("350.00"), funcionario.calcularTributos(new BigDecimal("10")));
    }

    @Test void exibeCamposEspecificosNoDetalhe() {
        var funcionario = new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("3000"), new BigDecimal("500"));
        assertTrue(funcionario.toString().contains("Benefício: R$ 500"));
    }
}
