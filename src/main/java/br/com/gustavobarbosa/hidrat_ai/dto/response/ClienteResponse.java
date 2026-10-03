package br.com.gustavobarbosa.hidrat_ai.dto.response;

import br.com.gustavobarbosa.hidrat_ai.domain.Endereco;

import java.util.UUID;

public record ClienteResponse(
    UUID id,
    String nome,
    String apelido,
    String contato,
    EstabelecimentoResponse estabelecimento,
    Endereco endereco
) { }
