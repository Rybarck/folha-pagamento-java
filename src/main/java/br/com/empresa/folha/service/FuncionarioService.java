package br.com.empresa.folha.service;

import br.com.empresa.folha.exception.FuncionarioJaCadastradoException;
import br.com.empresa.folha.exception.FuncionarioNaoEncontradoException;
import br.com.empresa.folha.model.Funcionario;
import br.com.empresa.folha.repository.FuncionarioRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

/** Coordena validações e operações oferecidas pelo sistema. */
public class FuncionarioService {
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) { this.repository = repository; }

    public void cadastrar(Funcionario funcionario) {
        if (repository.buscarPorCodigo(funcionario.getCodigo()) != null)
            throw new FuncionarioJaCadastradoException("Já existe funcionário com o código " + funcionario.getCodigo() + ".");
        if (repository.existeCpf(funcionario.getCpf()))
            throw new FuncionarioJaCadastradoException("Já existe funcionário com o CPF " + funcionario.getCpf() + ".");
        repository.salvar(funcionario);
    }

    public Collection<Funcionario> listarTodos() { return repository.listarTodos(); }

    public Funcionario buscarFuncionario(int codigo) {
        Funcionario funcionario = repository.buscarPorCodigo(codigo);
        if (funcionario == null) throw new FuncionarioNaoEncontradoException("Funcionário de código " + codigo + " não encontrado.");
        return funcionario;
    }

    public Funcionario buscarFuncionario(String cpf) {
        String normalizado = cpf.replaceAll("[.\\-\\s]", "");
        return repository.listarTodos().stream().filter(f -> f.getCpf().equals(normalizado)).findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário de CPF " + cpf + " não encontrado."));
    }

    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {
        List<Funcionario> resultado = repository.buscarPorNome(nome, buscaExata);
        if (resultado.isEmpty()) throw new FuncionarioNaoEncontradoException("Nenhum funcionário corresponde ao nome informado.");
        return resultado;
    }

    public void remover(int codigo) {
        if (!repository.remover(codigo)) throw new FuncionarioNaoEncontradoException("Funcionário de código " + codigo + " não encontrado.");
    }

    public BigDecimal calcularTotalFolha() {
        return repository.listarTodos().stream().map(Funcionario::calcularRemuneracao)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("RELATÓRIO DA FOLHA DE PAGAMENTO\n");
        for (Funcionario funcionario : repository.listarTodos()) relatorio.append(funcionario).append('\n');
        relatorio.append("Total da folha: R$ ").append(calcularTotalFolha());
        return relatorio.toString();
    }
}
