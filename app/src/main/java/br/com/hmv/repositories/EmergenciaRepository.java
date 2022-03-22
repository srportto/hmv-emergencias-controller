package br.com.hmv.repositories;

import br.com.hmv.models.entities.Emergencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmergenciaRepository extends JpaRepository<Emergencia, Long> {

    Optional<Emergencia> findEmergenciaByCodigoEmergencia(String codigoEmergencia);

    Page<Emergencia> findEmergenciaByCodigoStatusEmergencia(Long codigoStatusEmergencia, Pageable pageable);

    void deleteByCodigoEmergencia(String codigoEmergencia);
}
