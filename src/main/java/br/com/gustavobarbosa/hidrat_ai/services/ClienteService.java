package br.com.gustavobarbosa.hidrat_ai.services;

import br.com.gustavobarbosa.hidrat_ai.domain.Cliente;
import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.ClienteRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.ClienteResponse;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoNaoEncontradoException;
import br.com.gustavobarbosa.hidrat_ai.mappers.ClienteMapper;
import br.com.gustavobarbosa.hidrat_ai.repositories.ClienteRepository;
import br.com.gustavobarbosa.hidrat_ai.repositories.EstabelecimentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado.";
    private static final String ESTABELECIMENTO_NAO_ENCONTRADO = "Estabelecimento não encontrado.";


    private final ClienteRepository clienteRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ClienteMapper clienteMapper;

    public ClienteResponse buscarPorId(UUID id) {
        Cliente cliente = buscarCliente(id);

        return clienteMapper.paraDTO(cliente);
    }

    public List<ClienteResponse> buscarAtivos() {
        List<Cliente> clientes = clienteRepository.findAllByAtivoTrue();

        return clientes
                .stream()
                .map(clienteMapper::paraDTO)
                .toList();
    }

    @Transactional
    public ClienteResponse cadastrar(@Valid ClienteRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));

        Cliente cliente = clienteMapper.paraEntidade(request, estabelecimento);

        cliente = clienteRepository.save(cliente);

        return clienteMapper.paraDTO(cliente);
    }

    @Transactional
    public ClienteResponse editar(@Valid ClienteRequest request, UUID id) {
        Cliente cliente = buscarCliente(id);

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));

        cliente.setNome(request.nome());
        cliente.setApelido(request.apelido());
        cliente.setContato(request.contato());
        cliente.setEndereco(request.endereco());
        cliente.setEstabelecimento(estabelecimento);

        cliente = clienteRepository.save(cliente);

        return clienteMapper.paraDTO(cliente);
    }

    @Transactional
    public ClienteResponse desativar(UUID id) {
        Cliente cliente = buscarCliente(id);

        cliente.setAtivo(false);
        cliente.setDesativadoEm(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        cliente = clienteRepository.save(cliente);

        return clienteMapper.paraDTO(cliente);
    }

    private Cliente buscarCliente(UUID id) {
        return clienteRepository.findById(id)
                .filter(Cliente::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(CLIENTE_NAO_ENCONTRADO));
    }
}
