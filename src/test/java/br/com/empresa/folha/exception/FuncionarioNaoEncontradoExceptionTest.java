package br.com.empresa.folha.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FuncionarioNaoEncontradoExceptionTest {
    @Test void preservaMensagem() {
        var exception = new FuncionarioNaoEncontradoException("Não encontrado");
        assertEquals("Não encontrado", exception.getMessage());
    }
}
