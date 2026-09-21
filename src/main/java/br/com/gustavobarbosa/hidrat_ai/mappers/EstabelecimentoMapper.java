package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoCriacaoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoEdicaoRequest;
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

    public Estabelecimento paraEntidade(EstabelecimentoCriacaoRequest estabelecimentoCriacaoRequest) {
        return Estabelecimento.builder()
                .nome(estabelecimentoCriacaoRequest.nome().trim())
                .apelido(estabelecimentoCriacaoRequest.apelido().trim())
                .email(estabelecimentoCriacaoRequest.email().trim().toLowerCase())
                .senha(estabelecimentoCriacaoRequest.senha())
                .cpfCnpj(estabelecimentoCriacaoRequest.cpfCnpj().replaceAll("\\D", ""))
                .build();
    }

    public Estabelecimento paraEntidade(EstabelecimentoEdicaoRequest estabelecimentoCriacaoRequest) {
        return Estabelecimento.builder()
                .nome(estabelecimentoCriacaoRequest.nome().trim())
                .apelido(estabelecimentoCriacaoRequest.apelido().trim())
                .email(estabelecimentoCriacaoRequest.email().trim().toLowerCase())
                .cpfCnpj(estabelecimentoCriacaoRequest.cpfCnpj().replaceAll("\\D", ""))
                .build();
    }
}
