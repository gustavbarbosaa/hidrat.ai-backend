package br.com.gustavobarbosa.hidrat_ai.dto.response;

import java.util.UUID;

public record EstabelecimentoResponse(
   UUID id,
   String nome,
   String apelido,
   String email
) {}
