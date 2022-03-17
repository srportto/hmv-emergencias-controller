package br.com.hmv.repositories;

import br.com.hmv.models.entities.HabitoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitoPacienteRepository extends JpaRepository<HabitoPaciente, Long> {

}
