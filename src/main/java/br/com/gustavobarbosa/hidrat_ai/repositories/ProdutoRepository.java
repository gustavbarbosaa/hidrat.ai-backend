package br.com.gustavobarbosa.hidrat_ai.repositories;

import br.com.gustavobarbosa.hidrat_ai.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    List<Produto> findAllByAtivoTrue();
}
