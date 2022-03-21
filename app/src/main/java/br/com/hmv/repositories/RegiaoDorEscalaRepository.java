package br.com.hmv.repositories;

import br.com.hmv.models.entities.RegiaoDorEscala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegiaoDorEscalaRepository extends JpaRepository<RegiaoDorEscala, Long> {

    Optional<RegiaoDorEscala> findRegiaoDorEscalaByCodigoRegiaoDorAndCodigoEscalaDor(Integer regiaoDor, Integer escalaDor);

}
