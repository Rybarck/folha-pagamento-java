package br.com.empresa.folha.model;

import java.math.BigDecimal;
import java.util.Objects;

/** Dados comuns a todas as categorias de funcionário. */
public abstract class Funcionario implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String cpf;
    private final BigDecimal salarioBase;

    protected Funcionario(int codigo, String nome, String cpf, BigDecimal salarioBase) {
        if (codigo <= 0) throw new IllegalArgumentException("O código deve ser positivo.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome é obrigatório.");
        String cpfNormalizado = normalizarCpf(cpf);
        if (!cpfValido(cpfNormalizado)) throw new IllegalArgumentException("CPF inválido.");
        this.salarioBase = valorNaoNegativo(salarioBase, "O salário-base");
        this.codigo = codigo;
        this.nome = nome.trim();
        this.cpf = cpfNormalizado;
    }

    private static BigDecimal valorNaoNegativo(BigDecimal valor, String campo) {
        Objects.requireNonNull(valor, campo + " é obrigatório.");
        if (valor.signum() < 0) throw new IllegalArgumentException(campo + " não pode ser negativo.");
        return valor;
    }

    private static String normalizarCpf(String cpf) {
        if (cpf == null) return "";
        return cpf.replaceAll("[.\\-\\s]", "");
    }

    private static boolean cpfValido(String cpf) {
        if (!cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1) return false;
        int primeiro = digitoCpf(cpf, 9, 10);
        int segundo = digitoCpf(cpf, 10, 11);
        return cpf.charAt(9) - '0' == primeiro && cpf.charAt(10) - '0' == segundo;
    }

    private static int digitoCpf(String cpf, int tamanho, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) soma += (cpf.charAt(i) - '0') * (pesoInicial - i);
        int resto = (soma * 10) % 11;
        return resto == 10 ? 0 : resto;
    }

    public abstract BigDecimal calcularRemuneracao();

    @Override public final int getIdentificador() { return codigo; }
    public final int getCodigo() { return codigo; }
    public final String getNome() { return nome; }
    public final String getCpf() { return cpf; }
    public final BigDecimal getSalarioBase() { return salarioBase; }

    @Override public String toString() {
        return "Código: " + codigo + " | Nome: " + nome + " | CPF: " + cpf + " | Remuneração: " + calcularRemuneracao();
    }
}
