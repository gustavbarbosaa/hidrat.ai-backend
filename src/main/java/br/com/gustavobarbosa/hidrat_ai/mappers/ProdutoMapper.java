package br.com.gustavobarbosa.hidrat_ai.mappers;

import br.com.gustavobarbosa.hidrat_ai.domain.Cliente;
import br.com.gustavobarbosa.hidrat_ai.domain.Produto;
import br.com.gustavobarbosa.hidrat_ai.dto.ClienteDTO;
import br.com.gustavobarbosa.hidrat_ai.dto.ProdutoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public ProdutoDTO paraDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getValorRecarga(),
                produto.getValorComVasilhame(),
                produto.getValidade()
        );
    }

    public Produto paraEntidade(ProdutoDTO dto) {
        return Produto.builder()
                .nome(dto.nome())
                .marca(dto.marca())
                .valorRecarga(dto.valorRecarga())
                .valorComVasilhame(dto.valorComVasilhame())
                .validade(dto.validade())
                .build();
    }
}
