package br.com.empresa.folha.view;

import br.com.empresa.folha.exception.FuncionarioJaCadastradoException;
import br.com.empresa.folha.exception.FuncionarioNaoEncontradoException;
import br.com.empresa.folha.model.*;
import br.com.empresa.folha.service.FuncionarioService;
import java.math.BigDecimal;
import java.util.Scanner;

/** Interface de console para as operações principais da folha. */
public class FuncionarioView {
    private final FuncionarioService service;
    private final Scanner scanner;

    public FuncionarioView(FuncionarioService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n1 Cadastrar | 2 Listar | 3 Buscar código | 4 Remover | 5 Relatório | 0 Sair");
            try {
                switch (lerInteiro("Opção: ")) {
                    case 1 -> cadastrar();
                    case 2 -> service.listarTodos().forEach(System.out::println);
                    case 3 -> System.out.println(service.buscarFuncionario(lerInteiro("Código: ")));
                    case 4 -> { service.remover(lerInteiro("Código: ")); System.out.println("Funcionário removido."); }
                    case 5 -> System.out.println(service.gerarRelatorio());
                    case 0 -> executando = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (FuncionarioNaoEncontradoException | FuncionarioJaCadastradoException | IllegalArgumentException e) {
                System.out.println("Não foi possível concluir: " + e.getMessage());
            }
        }
    }

    private void cadastrar() {
        int codigo = lerInteiro("Código: ");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.println("Categoria: 1 CLT | 2 PJ | 3 Comissionado");
        int categoria = lerInteiro("Categoria: ");
        Funcionario funcionario = switch (categoria) {
            case 1 -> new FuncionarioCLT(codigo, nome, cpf, lerDecimal("Salário-base: "), lerDecimal("Benefício: "));
            case 2 -> new FuncionarioPJ(codigo, nome, cpf, lerDecimal("Valor por projeto: "), lerInteiro("Quantidade de projetos: "));
            case 3 -> new FuncionarioComissionado(codigo, nome, cpf, lerDecimal("Salário-base: "), lerDecimal("Vendas: "), lerDecimal("Comissão (%): "));
            default -> throw new IllegalArgumentException("Categoria inválida.");
        };
        service.cadastrar(funcionario);
        System.out.println("Funcionário cadastrado.");
    }

    private int lerInteiro(String rotulo) {
        System.out.print(rotulo);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private BigDecimal lerDecimal(String rotulo) {
        System.out.print(rotulo);
        return new BigDecimal(scanner.nextLine().trim().replace(',', '.'));
    }
}
