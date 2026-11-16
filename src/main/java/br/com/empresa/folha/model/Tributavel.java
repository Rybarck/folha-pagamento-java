package br.com.empresa.folha.model;

import java.math.BigDecimal;

/** Calcula o desconto percentual informado sobre a remuneração bruta. */
public interface Tributavel {
    BigDecimal calcularTributos(BigDecimal percentual);
}
