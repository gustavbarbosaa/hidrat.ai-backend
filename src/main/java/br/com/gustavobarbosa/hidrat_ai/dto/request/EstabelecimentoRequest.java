package br.com.gustavobarbosa.hidrat_ai.dto.request;

public record EstabelecimentoRequest(
        String nome,
        String apelido,
        String email,
        String senha
) { }
