package br.com.empresa.folha.model;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FuncionarioPJTest {
    @Test void calculaValorPorProjeto() {
        var funcionario = new FuncionarioPJ(2, "Beto", "111.444.777-35", new BigDecimal("1200"), 3);
        assertEquals(new BigDecimal("3600.00"), funcionario.calcularRemuneracao());
    }

    @Test void rejeitaQuantidadeDeProjetosNegativa() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioPJ(2, "Beto", "11144477735", new BigDecimal("1200"), -1));
    }

    @Test void exibeCamposEspecificosNoDetalhe() {
        var funcionario = new FuncionarioPJ(2, "Beto", "11144477735", new BigDecimal("1200"), 3);
        assertTrue(funcionario.toString().contains("Projetos: 3"));
    }
}
