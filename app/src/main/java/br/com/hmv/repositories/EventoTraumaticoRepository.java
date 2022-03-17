package br.com.hmv.repositories;

import br.com.hmv.models.entities.EventoTraumatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoTraumaticoRepository extends JpaRepository<EventoTraumatico, Long> {

}
