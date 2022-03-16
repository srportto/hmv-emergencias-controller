package br.com.hmv.repositories;

import br.com.hmv.models.entities.Sintomas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SintomasRepository extends JpaRepository<Sintomas, Long> {

}
