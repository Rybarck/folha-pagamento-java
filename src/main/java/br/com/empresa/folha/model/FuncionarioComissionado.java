package br.com.empresa.folha.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class FuncionarioComissionado extends Funcionario implements Tributavel {
    private final BigDecimal vendas;
    private final BigDecimal percentualComissao;

    public FuncionarioComissionado(int codigo, String nome, String cpf, BigDecimal salarioBase,
                                  BigDecimal vendas, BigDecimal percentualComissao) {
        super(codigo, nome, cpf, salarioBase);
        this.vendas = valorNaoNegativo(vendas, "As vendas");
        Objects.requireNonNull(percentualComissao, "O percentual de comissão é obrigatório.");
        if (percentualComissao.signum() < 0 || percentualComissao.compareTo(new BigDecimal("100")) > 0)
            throw new IllegalArgumentException("O percentual de comissão deve estar entre 0 e 100.");
        this.percentualComissao = percentualComissao;
    }

    private static BigDecimal valorNaoNegativo(BigDecimal valor, String campo) {
        Objects.requireNonNull(valor, campo + " são obrigatórias.");
        if (valor.signum() < 0) throw new IllegalArgumentException(campo + " não podem ser negativas.");
        return valor;
    }

    public BigDecimal getVendas() { return vendas; }
    public BigDecimal getPercentualComissao() { return percentualComissao; }

    @Override public BigDecimal calcularRemuneracao() {
        BigDecimal comissao = vendas.multiply(percentualComissao).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        return getSalarioBase().add(comissao).setScale(2, RoundingMode.HALF_UP);
    }

    @Override public BigDecimal calcularTributos(BigDecimal percentual) {
        Objects.requireNonNull(percentual, "O percentual é obrigatório.");
        if (percentual.signum() < 0 || percentual.compareTo(new BigDecimal("100")) > 0)
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 100.");
        return calcularRemuneracao().multiply(percentual).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    @Override public String toString() {
        return "Categoria: Comissionado | " + super.toString() + " | Vendas: R$ " + vendas
                + " | Comissão: " + percentualComissao + "%";
    }
}
