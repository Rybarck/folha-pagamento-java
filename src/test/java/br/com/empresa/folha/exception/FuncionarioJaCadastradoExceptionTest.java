package br.com.empresa.folha.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FuncionarioJaCadastradoExceptionTest {
    @Test void preservaMensagem() {
        var exception = new FuncionarioJaCadastradoException("Duplicado");
        assertEquals("Duplicado", exception.getMessage());
    }
}
