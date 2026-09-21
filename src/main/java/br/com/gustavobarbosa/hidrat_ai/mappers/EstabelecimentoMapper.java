package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EstabelecimentoResponse;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoMapper {
    public EstabelecimentoResponse paraResponse(Estabelecimento estabelecimento) {
        return new EstabelecimentoResponse(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getApelido(),
                estabelecimento.isAtivo()
        );
    }

    public Estabelecimento paraEntidade(EstabelecimentoRequest estabelecimentoRequest) {
        return Estabelecimento.builder()
                .nome(estabelecimentoRequest.nome().trim())
                .apelido(estabelecimentoRequest.apelido().trim())
                .email(estabelecimentoRequest.email().trim().toLowerCase())
                .senha(estabelecimentoRequest.senha())
                .cpfCnpj(estabelecimentoRequest.cpfCnpj().replaceAll("\\D", ""))
                .build();
    }
}
