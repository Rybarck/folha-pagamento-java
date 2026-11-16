package br.com.empresa.folha.repository;

import br.com.empresa.folha.model.Funcionario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Repositório em memória, indexado pelo código do funcionário. */
public class FuncionarioRepository {
    private final Map<Integer, Funcionario> funcionarios = new LinkedHashMap<>();

    public void salvar(Funcionario funcionario) { funcionarios.put(funcionario.getCodigo(), funcionario); }
    public Funcionario buscarPorCodigo(int codigo) { return funcionarios.get(codigo); }
    public Collection<Funcionario> listarTodos() { return new ArrayList<>(funcionarios.values()); }
    public boolean remover(int codigo) { return funcionarios.remove(codigo) != null; }
    public boolean existeCpf(String cpf) {
        return funcionarios.values().stream().anyMatch(f -> f.getCpf().equals(cpf.replaceAll("[.\\-\\s]", "")));
    }
    public List<Funcionario> buscarPorNome(String nome, boolean buscaExata) {
        String termo = nome.trim().toLowerCase();
        return funcionarios.values().stream()
                .filter(f -> buscaExata ? f.getNome().equalsIgnoreCase(termo) : f.getNome().toLowerCase().contains(termo))
                .toList();
    }
}
