package br.com.gustavobarbosa.hidrat_ai.services;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoCriacaoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoEdicaoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EstabelecimentoResponse;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoJaExistenteException;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoNaoEncontradoException;
import br.com.gustavobarbosa.hidrat_ai.mappers.EstabelecimentoMapper;
import br.com.gustavobarbosa.hidrat_ai.repositories.EstabelecimentoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
    private static final String ESTABELECIMENTO_NAO_ENCONTRADO = "Estabelecimento não encontrado.";
    private static final String ESTABELECIMENTO_JA_CADASTRADO = "Estabelecimento já cadastrado.";

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoMapper estabelecimentoMapper;

    public EstabelecimentoResponse buscarPorId(UUID id) {
        Estabelecimento estabelecimento = buscaEstabelecimento(id);

        return estabelecimentoMapper.paraResponse(estabelecimento);
    }

    public List<EstabelecimentoResponse> buscarAtivos() {
        List<Estabelecimento> estabelecimentosAtivos = estabelecimentoRepository.findAllByAtivoTrue();

        return estabelecimentosAtivos
                .stream()
                .map(estabelecimentoMapper::paraResponse)
                .toList();
    }

    public EstabelecimentoResponse cadastrar(@Valid EstabelecimentoCriacaoRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findByEmail(request.email()).orElse(null);

        if (estabelecimento != null) {
            throw new RecursoJaExistenteException(ESTABELECIMENTO_JA_CADASTRADO);
        }

        estabelecimento = estabelecimentoMapper.paraEntidade(request);

        estabelecimento = estabelecimentoRepository.save(estabelecimento);

        return estabelecimentoMapper.paraResponse(estabelecimento);
    }

    public EstabelecimentoResponse editar(@Valid EstabelecimentoEdicaoRequest request, UUID id) {
        Estabelecimento estabelecimento = buscaEstabelecimento(id);

        estabelecimento.setNome(request.nome());
        estabelecimento.setApelido(request.apelido());
        estabelecimento.setEmail(request.email());
        estabelecimento.setCpfCnpj(request.cpfCnpj());

        return estabelecimentoMapper.paraResponse(estabelecimento);
    }

    public EstabelecimentoResponse desativar(UUID id) {
        Estabelecimento estabelecimento = buscaEstabelecimento(id);

        estabelecimento.setAtivo(false);
        estabelecimento.setDesativadoEm(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        return estabelecimentoMapper.paraResponse(estabelecimento);
    }

    private Estabelecimento buscaEstabelecimento(UUID id) {
        return estabelecimentoRepository.findById(id)
                .filter(Estabelecimento::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));
    }
}
