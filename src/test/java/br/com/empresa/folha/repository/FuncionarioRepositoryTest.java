package br.com.empresa.folha.repository;

import static org.junit.jupiter.api.Assertions.*;
import br.com.empresa.folha.model.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FuncionarioRepositoryTest {
    @Test void armazenaBuscaListaERemove() {
        var repository = new FuncionarioRepository();
        var funcionario = new FuncionarioCLT(1, "Ana", "52998224725", BigDecimal.TEN, BigDecimal.ZERO);
        repository.salvar(funcionario);
        assertSame(funcionario, repository.buscarPorCodigo(1));
        assertEquals(1, repository.listarTodos().size());
        assertTrue(repository.remover(1));
        assertTrue(repository.listarTodos().isEmpty());
    }
}
