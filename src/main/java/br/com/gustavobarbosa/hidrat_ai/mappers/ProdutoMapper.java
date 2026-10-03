package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import br.com.gustavobarbosa.hidrat_ai.domain.Produto;
import br.com.gustavobarbosa.hidrat_ai.dto.request.ProdutoRequest;
import br.com.gustavobarbosa.hidrat_ai.dto.response.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {
    private final EstabelecimentoMapper estabelecimentoMapper;

    public ProdutoResponse paraDTO(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getValorRecarga(),
                produto.getValorComVasilhame(),
                estabelecimentoMapper.paraDTO(produto.getEstabelecimento()),
                produto.getValidade()
        );
    }

    public Produto paraEntidade(ProdutoRequest request, Estabelecimento estabelecimento) {
        return Produto.builder()
                .nome(request.nome())
                .marca(request.marca())
                .valorRecarga(request.valorRecarga())
                .valorComVasilhame(request.valorComVasilhame())
                .validade(request.validade())
                .estabelecimento(estabelecimento)
                .build();
    }
}
