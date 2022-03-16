package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.ConvenioRequestDTO;
import br.com.hmv.dtos.request.EnderecoInsertRequestDTO;
import br.com.hmv.dtos.responses.ConvenioDefaultResponseDTO;
import br.com.hmv.dtos.responses.EnderecoDefaultResponseDTO;
import br.com.hmv.models.entities.Convenio;
import br.com.hmv.models.entities.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface ConvenioMapper {
    ConvenioMapper INSTANCE = Mappers.getMapper(ConvenioMapper.class);

    Convenio deDtoParaEntity(ConvenioRequestDTO dto);

    ConvenioDefaultResponseDTO deEntityParaDto(Convenio entity);
}
