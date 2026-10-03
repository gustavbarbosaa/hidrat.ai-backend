package br.com.gustavobarbosa.hidrat_ai.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoResponse(
    UUID id,
    String nome,
    String marca,
    BigDecimal valorRecarga,
    BigDecimal valorComVasilhame,
    EstabelecimentoResponse estabelecimentoId,
    LocalDateTime validade
) { }
