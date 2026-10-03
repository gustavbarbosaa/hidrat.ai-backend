package br.com.gustavobarbosa.hidrat_ai.repositories;

import br.com.gustavobarbosa.hidrat_ai.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EntregaRepository extends JpaRepository<Entrega, UUID> {
    List<Entrega> findAllByAtivoTrue();
}
