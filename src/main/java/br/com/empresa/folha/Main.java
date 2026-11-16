package br.com.empresa.folha;

import br.com.empresa.folha.repository.FuncionarioRepository;
import br.com.empresa.folha.service.FuncionarioService;
import br.com.empresa.folha.view.FuncionarioView;
import java.util.Scanner;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var service = new FuncionarioService(new FuncionarioRepository());
        new FuncionarioView(service, new Scanner(System.in)).iniciar();
    }
}
