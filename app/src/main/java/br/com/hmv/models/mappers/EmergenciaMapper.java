package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.EmergenciaInsertRequestDTO;
import br.com.hmv.dtos.responses.EmergenciaDefaultResponseDTO;
import br.com.hmv.dtos.responses.EmergenciaForListResponseDTO;
import br.com.hmv.models.entities.Emergencia;
import br.com.hmv.models.enums.StatusEmergenciaEnum;
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
        dto.setStatusEmergencia(StatusEmergenciaEnum.obterStatusEmergencia(entity.getCodigoStatusEmergencia()));
        dto.getDetalhesPedidoAtendimento().setDataNascimento(entity.getDetalhesPedidoAtendimento().getDataNascimentoPaciente());
        dto.getDetalhesPedidoAtendimento().setRelatoMotivoPedidoAtendimento(entity.getDetalhesPedidoAtendimento().getRelatoEmTextoDoPedidoDeAtendimento());

        //inicializacao
        dto.getDetalhesPedidoAtendimento().getDores().clear();
        dto.getDetalhesPedidoAtendimento().getSintomas().clear();
        dto.getDetalhesPedidoAtendimento().getHabitosPaciente().clear();
        dto.getDetalhesPedidoAtendimento().getEventosTraumaticos().clear();
    }

    public abstract EmergenciaForListResponseDTO deEntityParaListDto(Emergencia entity);

    @AfterMapping
    protected void ajustaDepoisDeMapearEntityParaListDto(Emergencia entity, @MappingTarget EmergenciaForListResponseDTO dto) {
        dto.setStatusEmergencia(StatusEmergenciaEnum.obterStatusEmergencia(entity.getCodigoStatusEmergencia()));
        dto.getDetalhesPedidoAtendimento().setDataNascimento(entity.getDetalhesPedidoAtendimento().getDataNascimentoPaciente());
        dto.getDetalhesPedidoAtendimento().setRelatoMotivoPedidoAtendimento(entity.getDetalhesPedidoAtendimento().getRelatoEmTextoDoPedidoDeAtendimento());

        //inicializacao
        dto.getDetalhesPedidoAtendimento().getDores().clear();
        dto.getDetalhesPedidoAtendimento().getSintomas().clear();
        dto.getDetalhesPedidoAtendimento().getHabitosPaciente().clear();
        dto.getDetalhesPedidoAtendimento().getEventosTraumaticos().clear();
    }
}
