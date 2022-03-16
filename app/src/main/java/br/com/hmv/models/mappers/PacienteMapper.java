package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.PacienteInsertRequestDTO;
import br.com.hmv.dtos.responses.PacienteDefaultResponseDTO;
import br.com.hmv.dtos.responses.PacienteInsertResponseDTO;
import br.com.hmv.models.entities.Paciente;
import br.com.hmv.models.enums.CadastroPacienteEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public abstract class PacienteMapper {
    public static final PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    public abstract Paciente deDtoParaEntity(PacienteInsertRequestDTO dto);

    @BeforeMapping
    protected void preparaAntesDeMapearEntityParaDtoInsert(Paciente entity, @MappingTarget PacienteInsertResponseDTO dto) {
        dto.setIndicadorCadastro(CadastroPacienteEnum.obterStatusCadastroPaciente(entity.getIndicadorTipoCadastroRealizado()));
    }

    public abstract PacienteInsertResponseDTO deEntityParaDtoInsert(Paciente entity);


//    @BeforeMapping
//    protected void preparaAntesDeMapearEntityParaDtoDefault(Paciente entity, @MappingTarget PacienteDefaultResponseDTO dto) {
//        dto.setIndicadorCadastro(CadastroPacienteEnum.obterStatusCadastroPaciente(entity.getIndicadorTipoCadastroRealizado()));
//    }


    public abstract PacienteDefaultResponseDTO deEntityParaDtoDefault(Paciente entity);


    @AfterMapping
    protected void ajustaDepoisDeMapearEntityParaDtoDefault(Paciente entity, @MappingTarget PacienteDefaultResponseDTO dto) {
        dto.setIndicadorCadastro(CadastroPacienteEnum.obterStatusCadastroPaciente(entity.getIndicadorTipoCadastroRealizado()));
    }


//    @BeforeMapping
//    protected void preparaAntesDeMapearEntityParaListDto(Paciente entity, @MappingTarget FuncionarioForListResponseDTO dto) {
//        dto.setStatusFuncionario(StatusFuncionarioEnum.obterStatusFuncionario(entity.getCodigoStatusFuncionario()));
//        dto.setGrupoFuncaoFuncionario(GrupoFuncaoFuncionarioEnum.obterGrupoFuncaoFuncionario(entity.getCodigoGrupoFuncao()));
//    }
//
//    public abstract FuncionarioForListResponseDTO deEntityParaRespresentacaoEmLista(Paciente entity);
}
