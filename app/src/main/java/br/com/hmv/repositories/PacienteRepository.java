package br.com.hmv.repositories;

import br.com.hmv.models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findPacienteByIdPaciente(String idPaciente);

    void deleteByIdPaciente(String idPaciente);

}
