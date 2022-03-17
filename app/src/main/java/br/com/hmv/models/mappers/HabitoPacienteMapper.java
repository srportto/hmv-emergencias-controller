package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.HabitoPacienteRequestDTO;
import br.com.hmv.dtos.responses.HabitoPacienteDefaultResponseDTO;
import br.com.hmv.models.entities.HabitoPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface HabitoPacienteMapper {
    HabitoPacienteMapper INSTANCE = Mappers.getMapper(HabitoPacienteMapper.class);

    HabitoPaciente deDtoParaEntity(HabitoPacienteRequestDTO dto);

    HabitoPacienteDefaultResponseDTO deEntityParaDto(HabitoPaciente entity);
}
