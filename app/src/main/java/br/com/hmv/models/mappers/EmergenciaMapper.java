package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.EmergenciaInsertRequestDTO;
import br.com.hmv.dtos.responses.EmergenciaDefaultResponseDTO;
import br.com.hmv.models.entities.Emergencia;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public abstract class EmergenciaMapper {
    public static final EmergenciaMapper INSTANCE = Mappers.getMapper(EmergenciaMapper.class);

    public abstract Emergencia deDtoParaEntity(EmergenciaInsertRequestDTO dto);


    public abstract EmergenciaDefaultResponseDTO deEntityParaDtoDefault(Emergencia entity);


    @AfterMapping
    protected void ajustaDepoisDeMapearEntityParaDtoDefault(Emergencia entity, @MappingTarget EmergenciaDefaultResponseDTO dto) {
        // todo -> Fazendo
        //dto.setIndicadorCadastro(CadastroPacienteEnum.obterStatusCadastroPaciente(entity.getIndicadorTipoCadastroRealizado()));
    }


}
