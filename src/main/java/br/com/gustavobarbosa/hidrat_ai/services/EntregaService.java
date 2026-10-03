package br.com.gustavobarbosa.hidrat_ai.services;

import br.com.gustavobarbosa.hidrat_ai.domain.Entrega;
import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EntregaRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EntregaResponse;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoNaoEncontradoException;
import br.com.gustavobarbosa.hidrat_ai.mappers.EntregaMapper;
import br.com.gustavobarbosa.hidrat_ai.repositories.EntregaRepository;
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
public class EntregaService {
    private static final String ENTREGA_NAO_ENCONTRADA = "Entrega não encontrada.";
    private static final String ESTABELECIMENTO_NAO_ENCONTRADO = "Estabelecimento não encontrado.";

    private final EntregaRepository entregaRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EntregaMapper entregaMapper;

    public EntregaResponse buscarPorId(UUID id) {
        Entrega entrega = buscarEntrega(id);

        return entregaMapper.paraDTO(entrega);
    }

    public List<EntregaResponse> buscarAtivos() {
        List<Entrega> entregas = entregaRepository.findAllByAtivoTrue();

        return entregas
                .stream()
                .map(entregaMapper::paraDTO)
                .toList();
    }

    @Transactional
    public EntregaResponse cadastrar(@Valid EntregaRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));

        Entrega entrega = entregaMapper.paraEntidade(request, estabelecimento);

        entrega = entregaRepository.save(entrega);

        return entregaMapper.paraDTO(entrega);
    }

    @Transactional
    public EntregaResponse editar(@Valid EntregaRequest request, UUID id) {
        Entrega entrega = buscarEntrega(id);

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));

        entrega.setValor(request.valor());
        entrega.setEstabelecimento(estabelecimento);
        entrega.setEndereco(request.endereco());

        entrega = entregaRepository.save(entrega);

        return entregaMapper.paraDTO(entrega);
    }

    @Transactional
    public EntregaResponse desativar(UUID id) {
        Entrega entrega = buscarEntrega(id);

        entrega.setAtivo(false);
        entrega.setDesativadoEm(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        entrega = entregaRepository.save(entrega);

        return entregaMapper.paraDTO(entrega);
    }

    private Entrega buscarEntrega(UUID id) {
        return entregaRepository
                .findById(id)
                .filter(Entrega::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(ENTREGA_NAO_ENCONTRADA));
    }
}
