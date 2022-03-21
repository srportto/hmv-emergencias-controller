package br.com.hmv.repositories;

import br.com.hmv.models.entities.Emergencia;
import br.com.hmv.models.entities.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergenciaRepository extends JpaRepository<Emergencia, Long> {

}
