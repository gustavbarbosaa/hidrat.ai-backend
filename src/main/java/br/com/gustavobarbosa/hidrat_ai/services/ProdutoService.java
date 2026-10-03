package br.com.gustavobarbosa.hidrat_ai.services;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.domain.Produto;
import br.com.gustavobarbosa.hidrat_ai.dto.request.ProdutoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.ProdutoResponse;
import br.com.gustavobarbosa.hidrat_ai.exceptions.RecursoNaoEncontradoException;
import br.com.gustavobarbosa.hidrat_ai.mappers.ProdutoMapper;
import br.com.gustavobarbosa.hidrat_ai.repositories.EstabelecimentoRepository;
import br.com.gustavobarbosa.hidrat_ai.repositories.ProdutoRepository;
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
public class ProdutoService {
    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado.";
    private static final String ESTABELECIMENTO_NAO_ENCONTRADO = "Estabelecimento não encontrado.";

    private final ProdutoRepository produtoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoResponse buscarPorId(UUID id) {
        Produto produto = buscarProduto(id);

        return produtoMapper.paraDTO(produto);
    }

    public List<ProdutoResponse> buscarAtivos() {
        List<Produto> produtos = produtoRepository.findAllByAtivoTrue();

        return produtos
                .stream()
                .map(produtoMapper::paraDTO)
                .toList();
    }

    @Transactional
    public ProdutoResponse cadastrar(@Valid ProdutoRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));

        Produto produto = produtoMapper.paraEntidade(request, estabelecimento);

        produto = produtoRepository.save(produto);

        return produtoMapper.paraDTO(produto);
    }

    @Transactional
    public ProdutoResponse editar(@Valid ProdutoRequest request, UUID id) {
        Produto produto = buscarProduto(id);

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(ESTABELECIMENTO_NAO_ENCONTRADO));

        produto.setNome(request.nome());
        produto.setMarca(request.marca());
        produto.setValorRecarga(request.valorRecarga());
        produto.setValorComVasilhame(request.valorComVasilhame());
        produto.setValidade(request.validade());
        produto.setEstabelecimento(estabelecimento);

        produto = produtoRepository.save(produto);

        return produtoMapper.paraDTO(produto);
    }

    @Transactional
    public ProdutoResponse desativar(UUID id) {
        Produto produto = buscarProduto(id);

        produto.setAtivo(false);
        produto.setDesativadoEm(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        produto = produtoRepository.save(produto);

        return produtoMapper.paraDTO(produto);
    }

    private Produto buscarProduto(UUID id) {
        return produtoRepository
                .findById(id)
                .filter(Produto::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(PRODUTO_NAO_ENCONTRADO));
    }
}
