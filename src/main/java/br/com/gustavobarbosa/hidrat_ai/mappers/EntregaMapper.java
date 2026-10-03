package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Entrega;
import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EntregaRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EntregaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntregaMapper {
    private final EstabelecimentoMapper estabelecimentoMapper;

    public EntregaResponse paraDTO(Entrega entrega) {
        return new EntregaResponse(
          entrega.getId(),
          entrega.getValor(),
          estabelecimentoMapper.paraDTO(entrega.getEstabelecimento()),
          entrega.getEndereco(),
          entrega.isAtivo()
        );
    }

    public Entrega paraEntidade(EntregaRequest request, Estabelecimento estabelecimento) {
        return Entrega.builder()
                .valor(request.valor())
                .estabelecimento(estabelecimento)
                .endereco(request.endereco())
                .build();
    }
}
