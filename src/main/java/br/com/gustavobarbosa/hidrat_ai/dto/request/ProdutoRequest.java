package br.com.gustavobarbosa.hidrat_ai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoRequest(
    UUID id,
    @NotBlank(message = "O campo nome é obrigatório.")
    @Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres.")
    String nome,
    @Size(max = 100, message = "O campo marca deve ter no máximo 100 caracteres.")
    String marca,
    @NotBlank(message = "O campo valor recarga é obrigatório.")
    @Positive(message = "O campo de valor recarga deve possuir um valor positivo.")
    BigDecimal valorRecarga,
    @NotBlank(message = "O campo valor com vasilhame é obrigatório.")
    @Positive(message = "O campo de valor com vasilhame deve possuir um valor positivo.")
    BigDecimal valorComVasilhame,
    @NotBlank(message = "O campo estabelecimento é obrigatório.")
    UUID estabelecimentoId,
    LocalDateTime validade
) { }
