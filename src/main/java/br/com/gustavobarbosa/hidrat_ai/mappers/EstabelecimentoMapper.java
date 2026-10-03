package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoCriacaoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EstabelecimentoResponse;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoMapper {
    public EstabelecimentoResponse paraDTO(Estabelecimento estabelecimento) {
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
}
