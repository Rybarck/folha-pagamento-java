package br.com.empresa.folha.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class FuncionarioCLT extends Funcionario implements Tributavel {
    private final BigDecimal beneficio;

    public FuncionarioCLT(int codigo, String nome, String cpf, BigDecimal salarioBase, BigDecimal beneficio) {
        super(codigo, nome, cpf, salarioBase);
        this.beneficio = validarValor(beneficio, "O benefício");
    }

    private static BigDecimal validarValor(BigDecimal valor, String campo) {
        Objects.requireNonNull(valor, campo + " é obrigatório.");
        if (valor.signum() < 0) throw new IllegalArgumentException(campo + " não pode ser negativo.");
        return valor;
    }

    public BigDecimal getBeneficio() { return beneficio; }

    @Override public BigDecimal calcularRemuneracao() {
        return getSalarioBase().add(beneficio).setScale(2, RoundingMode.HALF_UP);
    }

    @Override public BigDecimal calcularTributos(BigDecimal percentual) {
        return calcularDesconto(percentual);
    }

    @Override public String toString() {
        return "Categoria: CLT | " + super.toString() + " | Benefício: R$ " + beneficio;
    }

    private BigDecimal calcularDesconto(BigDecimal percentual) {
        Objects.requireNonNull(percentual, "O percentual é obrigatório.");
        if (percentual.signum() < 0 || percentual.compareTo(new BigDecimal("100")) > 0)
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 100.");
        return calcularRemuneracao().multiply(percentual).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
}
