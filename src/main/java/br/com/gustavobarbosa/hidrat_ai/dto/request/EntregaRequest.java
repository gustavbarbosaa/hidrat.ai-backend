package br.com.gustavobarbosa.hidrat_ai.dto.request;

import br.com.gustavobarbosa.hidrat_ai.domain.Endereco;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record EntregaRequest(
    @NotBlank(message = "O campo valor é obrigatório.")
    BigDecimal valor,
    @NotBlank(message = "O campo de estabelecimento é obrigatório.")
    UUID estabelecimentoId,
    Endereco endereco
) { }
