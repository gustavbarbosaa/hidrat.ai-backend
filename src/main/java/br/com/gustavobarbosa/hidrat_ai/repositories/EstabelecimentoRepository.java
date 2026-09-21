package br.com.gustavobarbosa.hidrat_ai.repositories;

import br.com.gustavobarbosa.hidrat_ai.domain.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, UUID> {
}
