package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Cliente;
import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.ClienteRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteMapper {
    private final EstabelecimentoMapper estabelecimentoMapper;

    public ClienteResponse paraDTO(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getApelido(),
                cliente.getContato(),
                estabelecimentoMapper.paraDTO(cliente.getEstabelecimento()),
                cliente.getEndereco()
        );
    }

    public Cliente paraEntidade(ClienteRequest request, Estabelecimento estabelecimento) {
        return Cliente.builder()
                .nome(request.nome())
                .apelido(request.apelido())
                .contato(request.contato())
                .endereco(request.endereco())
                .estabelecimento(estabelecimento)
                .build();
    }
}
