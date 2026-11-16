package br.com.empresa.folha.service;

import static org.junit.jupiter.api.Assertions.*;
import br.com.empresa.folha.exception.*;
import br.com.empresa.folha.model.*;
import br.com.empresa.folha.repository.FuncionarioRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FuncionarioServiceTest {
    private FuncionarioCLT ana() { return new FuncionarioCLT(1, "Ana", "52998224725", new BigDecimal("3000"), new BigDecimal("200")); }

    @Test void cadastraBuscaListaRelatorioETotaliza() {
        var service = new FuncionarioService(new FuncionarioRepository());
        service.cadastrar(ana());
        assertSame(service.buscarFuncionario(1), service.buscarFuncionario("52998224725"));
        assertEquals(1, service.buscarFuncionario("ana", true).size());
        assertTrue(service.gerarRelatorio().contains("3200.00"));
        assertEquals(new BigDecimal("3200.00"), service.calcularTotalFolha());
        assertEquals(1, service.listarTodos().size());
    }

    @Test void impedeCodigoOuCpfDuplicado() {
        var service = new FuncionarioService(new FuncionarioRepository());
        service.cadastrar(ana());
        assertThrows(FuncionarioJaCadastradoException.class, () -> service.cadastrar(ana()));
        assertThrows(FuncionarioJaCadastradoException.class, () -> service.cadastrar(new FuncionarioPJ(2, "Bia", "52998224725", BigDecimal.TEN, 1)));
    }

    @Test void sinalizaBuscaERemocaoInexistentes() {
        var service = new FuncionarioService(new FuncionarioRepository());
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario(9));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.remover(9));
    }

    @Test void removerFuncionarioCadastrado() {
        var service = new FuncionarioService(new FuncionarioRepository());
        service.cadastrar(ana());
        service.remover(1);
        assertTrue(service.listarTodos().isEmpty());
    }
}
