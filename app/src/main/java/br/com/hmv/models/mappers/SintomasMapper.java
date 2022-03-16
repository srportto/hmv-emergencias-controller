package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.SintomaRequestDTO;
import br.com.hmv.dtos.responses.SintomaDefaultResponseDTO;
import br.com.hmv.models.entities.Sintomas;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface SintomasMapper {
    SintomasMapper INSTANCE = Mappers.getMapper(SintomasMapper.class);

    Sintomas deDtoParaEntity(SintomaRequestDTO dto);

    SintomaDefaultResponseDTO deEntityParaDto(Sintomas entity);
}
