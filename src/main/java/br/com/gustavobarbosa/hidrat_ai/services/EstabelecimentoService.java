package br.com.gustavobarbosa.hidrat_ai.services;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.dto.request.EstabelecimentoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.EstabelecimentoResponse;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoJaExistenteException;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoNaoEncontradoException;
import br.com.gustavobarbosa.hidrat_ai.mappers.EstabelecimentoMapper;
import br.com.gustavobarbosa.hidrat_ai.repositories.EstabelecimentoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public EstabelecimentoResponse cadastrar(@Valid EstabelecimentoRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findByEmail(request.email()).orElse(null);

        if (estabelecimento != null) {
            throw new RecursoJaExistenteException(ESTABELECIMENTO_JA_CADASTRADO);
        }

        estabelecimento = estabelecimentoMapper.paraEntidade(request);

        estabelecimento = estabelecimentoRepository.save(estabelecimento);

        return estabelecimentoMapper.paraResponse(estabelecimento);
    }

    private Estabelecimento buscaEstabelecimento(UUID id) {
        return estabelecimentoRepository.findById(id)
                .filter(Estabelecimento::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));
    }
}
