package br.com.gustavobarbosa.hidrat_ai.repositories;

import br.com.gustavobarbosa.hidrat_ai.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findAllByAtivoTrue();
}
