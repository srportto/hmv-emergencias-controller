package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.EventoTraumaticoRequestDTO;
import br.com.hmv.dtos.responses.EventoTraumaticoDefaultResponseDTO;
import br.com.hmv.models.entities.EventoTraumatico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface EventoTraumaticoMapper {
    EventoTraumaticoMapper INSTANCE = Mappers.getMapper(EventoTraumaticoMapper.class);

    EventoTraumatico deDtoParaEntity(EventoTraumaticoRequestDTO dto);

    EventoTraumaticoDefaultResponseDTO deEntityParaDto(EventoTraumatico entity);
}
