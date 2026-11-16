package br.com.empresa.folha.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import br.com.empresa.folha.repository.FuncionarioRepository;
import br.com.empresa.folha.service.FuncionarioService;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class FuncionarioViewTest {
    @Test void encerraQuandoUsuarioEscolheSair() {
        var entrada = new Scanner(new ByteArrayInputStream("0\n".getBytes()));
        var view = new FuncionarioView(new FuncionarioService(new FuncionarioRepository()), entrada);
        assertDoesNotThrow(view::iniciar);
    }
}
