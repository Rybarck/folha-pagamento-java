package br.com.empresa.folha.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Prestador PJ remunerado por projeto; o valor por projeto ocupa o campo base comum. */
public final class FuncionarioPJ extends Funcionario {
    private final int quantidadeProjetos;

    public FuncionarioPJ(int codigo, String nome, String cpf, BigDecimal valorPorProjeto, int quantidadeProjetos) {
        super(codigo, nome, cpf, valorPorProjeto);
        Objects.requireNonNull(valorPorProjeto, "O valor por projeto é obrigatório.");
        if (quantidadeProjetos < 0) throw new IllegalArgumentException("A quantidade de projetos não pode ser negativa.");
        this.quantidadeProjetos = quantidadeProjetos;
    }

    public BigDecimal getValorPorProjeto() { return getSalarioBase(); }
    public int getQuantidadeProjetos() { return quantidadeProjetos; }

    @Override public BigDecimal calcularRemuneracao() {
        return getValorPorProjeto().multiply(BigDecimal.valueOf(quantidadeProjetos)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override public String toString() {
        return "Categoria: PJ | " + super.toString() + " | Valor por projeto: R$ " + getValorPorProjeto()
                + " | Projetos: " + quantidadeProjetos;
    }
}
