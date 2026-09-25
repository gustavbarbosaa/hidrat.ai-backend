package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Cliente;
import br.com.gustavobarbosa.hidrat_ai.dto.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteDTO paraDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getNome(),
                cliente.getApelido(),
                cliente.getContato(),
                cliente.getEndereco()
        );
    }

    public Cliente paraEntidade(ClienteDTO dto) {
        return Cliente.builder()
                .nome(dto.nome())
                .apelido(dto.apelido())
                .contato(dto.contato())
                .endereco(dto.endereco())
                .build();
    }
}
