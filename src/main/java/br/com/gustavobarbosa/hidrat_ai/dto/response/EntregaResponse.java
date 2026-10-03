package br.com.gustavobarbosa.hidrat_ai.dto.response;

import br.com.gustavobarbosa.hidrat_ai.domain.Endereco;

import java.math.BigDecimal;
import java.util.UUID;

public record EntregaResponse(
    UUID id,
    BigDecimal valor,
    EstabelecimentoResponse estabelecimento,
    Endereco endereco,
    boolean ativo
) { }
