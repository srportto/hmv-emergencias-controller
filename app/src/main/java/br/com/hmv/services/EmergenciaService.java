package br.com.hmv.services;

import br.com.hmv.dtos.request.EmergenciaInsertRequestDTO;
import br.com.hmv.dtos.responses.DorDefaultResponseDTO;
import br.com.hmv.dtos.responses.EmergenciaDefaultResponseDTO;
import br.com.hmv.dtos.responses.EmergenciaForListResponseDTO;
import br.com.hmv.dtos.responses.EventoTraumaticoEmergenciaDefaultResponsetDTO;
import br.com.hmv.dtos.responses.HabitoPacienteEmergenciaDefaultResponsetDTO;
import br.com.hmv.dtos.responses.SintomaEmergenciaDefaultResponsetDTO;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Emergencia;
import br.com.hmv.models.entities.RegiaoDorEscala;
import br.com.hmv.models.enums.ScoreEscalaDeDorDoPacienteEnum;
import br.com.hmv.models.enums.ScoreRegiaoDorEnum;
import br.com.hmv.models.enums.StatusEmergenciaEnum;
import br.com.hmv.models.mappers.EmergenciaMapper;
import br.com.hmv.repositories.EmergenciaRepository;
import br.com.hmv.repositories.EventoTraumaticoRepository;
import br.com.hmv.repositories.HabitoPacienteRepository;
import br.com.hmv.repositories.RegiaoDorEscalaRepository;
import br.com.hmv.repositories.SintomaRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmergenciaService {
    private static Logger logger = LoggerFactory.getLogger(EmergenciaService.class);
    private EmergenciaRepository emergenciaRepository;
    private SintomaRepository sintomaRepository;
    private HabitoPacienteRepository habitoPacienteRepository;
    private EventoTraumaticoRepository eventoTraumaticoRepository;
    private RegiaoDorEscalaRepository regiaoDorEscalaRepository;

    @Transactional
    public EmergenciaDefaultResponseDTO criacao(EmergenciaInsertRequestDTO dto) {
        String logCode = "criacao(EmergenciaInsertRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = emergenciaRepository.save(entity);

        logger.info("{} - recurso incluido com sucesso {}", logCode, entity);
        return entityToDefaultResponseDto(entity);
    }

    //    @Transactional
//    public SintomaDefaultResponseDTO updateScore(Long codigoEmergencia, SintomaUpdateScoreRequestDTO dto) {
//        String logCode = "updateScore(String, SintomaUpdateScoreRequestDTO)";
//        logger.info("{} - solicitacao de atualizacao de score do sintoma {}", logCode, dto);
//
//        try {
//            var objOptional = sintomaRepository.findById(codigoEmergencia);
//            Sintoma entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + codigoEmergencia));
//
//            entity.setScore(dto.getScore());
//            var entityAtualizada = sintomaRepository.save(entity);
//
//            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entityAtualizada);
//            return SintomaMapper.INSTANCE.deEntityParaDto(entityAtualizada);
//
//        } catch (EntityNotFoundException e) {
//            logger.warn("{} - recurso nao encontrado id: {} ", codigoEmergencia);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + codigoEmergencia);
//        }
//    }
//
//
//    @Transactional(readOnly = true)
//    public Page<SintomaDefaultResponseDTO> findAllPaged(Pageable pageable) {
//        String logCode = "findAllPaged(Pageable)";
//        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);
//
//        Page<Sintoma> list = sintomaRepository.findAll(pageable);
//        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
//        return list.map(itemFuncionarioEntity -> SintomaMapper.INSTANCE.deEntityParaDto(itemFuncionarioEntity));
//    }
//
    @Transactional(readOnly = true)
    public Page<EmergenciaForListResponseDTO> findAllPagedPorStatusEmergencia(StatusEmergenciaEnum statusEmergencia, Pageable pageable) {
        String logCode = "findAllPagedPorStatusEmergencia(StatusEmergenciaEnum,Pageable)";
        logger.info("{} - consulta paginada de recursos vide parametros {} e grupo funcao {}", logCode, pageable, statusEmergencia);

        var codigoStatusEmergencia = statusEmergencia.getCodigoStatusEmergencia();
        Page<Emergencia> list = emergenciaRepository.findEmergenciaByCodigoStatusEmergencia(codigoStatusEmergencia, pageable);
        logger.info("{} - consulta paginada de recursos por grupo de funcao realizada com sucesso: {}", logCode, list);
        return list.map(itemEmergenciaEntity -> EmergenciaMapper.INSTANCE.deEntityParaListDto(itemEmergenciaEntity));
    }

    @Transactional(readOnly = true)
    public EmergenciaDefaultResponseDTO findByCodigoEmergencia(String codigoEmergencia) {
        String logCode = "findByCodigoEmergencia(String)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, codigoEmergencia);

        Optional<Emergencia> obj = emergenciaRepository.findEmergenciaByCodigoEmergencia(codigoEmergencia);
        Emergencia entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + codigoEmergencia));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return entityToDefaultResponseDto(entity);
    }
//
//    @Transactional
//    public void delete(Long id) {
//        String logCode = "delete(Long)";
//        logger.info("{} - deletando recurso: {}", logCode, id);
//
//        try {
//            sintomaRepository.deleteById(id);
//            logger.info("{} - recurso deletado com sucesso: {}", logCode, id);
//
//        } catch (EmptyResultDataAccessException e) {
//            logger.warn("{} - recurso nao encontrado: {}", logCode, id);
//            throw new ResourceNotFoundException("Convenio nao encontrado id: " + id);
//
//        } catch (DataIntegrityViolationException e) {
//            logger.warn("{} - erro de integridade de dados: {}", logCode, id);
//            throw new DatabaseException("Integrity violation - Ao deletar convenio id: " + id);
//
//        } catch (Exception e) {
//            logger.warn("{} - erro ao processar requisicao: {}", logCode, id);
//            throw new DatabaseException(e.getMessage());
//        }
//    }

    private Emergencia dtoToEntityOnCreate(EmergenciaInsertRequestDTO dto) {
        String logCode = "dtoToEntityOnCreate(EmergenciaDefaultResponseDTO)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        var entity = EmergenciaMapper.INSTANCE.deDtoParaEntity(dto);
        entity.setCodigoEmergencia(UUID.randomUUID().toString());
        entity.getDetalhesPedidoAtendimento().setDataNascimentoPaciente(dto.getDetalhesPedidoAtendimento().getDataNascimento());
        entity.getDetalhesPedidoAtendimento().setRelatoEmTextoDoPedidoDeAtendimento(dto.getDetalhesPedidoAtendimento().getRelatoMotivoPedidoAtendimento());
        entity.getDetalhesPedidoAtendimento().setCodigoDetalhesPedido(UUID.randomUUID().toString());
        entity.setCodigoStatusEmergencia(dto.getStatusEmergencia().getCodigoStatusEmergencia());
        entity.setScore(9999);
        var detalhesPedidoAtendimento = dto.getDetalhesPedidoAtendimento();

        if (detalhesPedidoAtendimento.getDores() != null) {
            var dores = detalhesPedidoAtendimento.getDores();
            entity.getDetalhesPedidoAtendimento().getDores().clear();

            dores.forEach(itemDores -> {
                var codigoRegiaoDor = itemDores.getRegiaoDor().getCodigoRegiaoDor();
                var codigoEscalaDor = itemDores.getEscalaDeDorDoPaciente().getCodigoEscalaDeDor();

                var objOptional = regiaoDorEscalaRepository.findRegiaoDorEscalaByCodigoRegiaoDorAndCodigoEscalaDor(codigoRegiaoDor, codigoEscalaDor);

                if (objOptional.isPresent()) {
                    var regiaEscalaDor = objOptional.get();
                    entity.getDetalhesPedidoAtendimento().getDores().add(regiaEscalaDor);

                } else {
                    throw new ResourceNotFoundException("Regiao e escala de dor nao encontradas");
                }
            });
        }

        if (detalhesPedidoAtendimento.getSintomas() != null) {
            var sintomas = detalhesPedidoAtendimento.getSintomas();
            entity.getDetalhesPedidoAtendimento().getSintomas().clear();

            sintomas.forEach(itemSintomas -> {
                var sintomaId = itemSintomas.getIdSintoma().longValue();
                var sintoma = sintomaRepository.getOne(sintomaId);

                entity.getDetalhesPedidoAtendimento().getSintomas().add(sintoma);
            });
        }

        if (detalhesPedidoAtendimento.getHabitosPaciente() != null) {
            var habitosPaciente = detalhesPedidoAtendimento.getHabitosPaciente();
            entity.getDetalhesPedidoAtendimento().getHabitosPaciente().clear();

            habitosPaciente.forEach(itemHabitoPaciente -> {
                var habitoPacienteId = itemHabitoPaciente.getIdHabito().longValue();
                var habitoPaciente = habitoPacienteRepository.getOne(habitoPacienteId);

                entity.getDetalhesPedidoAtendimento().getHabitosPaciente().add(habitoPaciente);
            });
        }

        if (detalhesPedidoAtendimento.getEventosTraumaticos() != null) {
            var eventosTraumaticos = detalhesPedidoAtendimento.getEventosTraumaticos();
            entity.getDetalhesPedidoAtendimento().getEventosTraumaticos().clear();

            eventosTraumaticos.forEach(itemEventosTraumaticos -> {
                var eventoTraumaticoId = itemEventosTraumaticos.getIdEvento().longValue();
                var eventoTraumaticoPaciente = eventoTraumaticoRepository.getOne(eventoTraumaticoId);

                entity.getDetalhesPedidoAtendimento().getEventosTraumaticos().add(eventoTraumaticoPaciente);
            });
        }

        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
        return entity;
    }

    private EmergenciaDefaultResponseDTO entityToDefaultResponseDto(Emergencia entity) {
        String logCode = "entityToDefaultResponseDto(Emergencia)";
        logger.info("{} - convertendo entity para response default {}", logCode, entity);

        var responseDto = EmergenciaMapper.INSTANCE.deEntityParaDtoDefault(entity);

        if (entity.getDetalhesPedidoAtendimento().getDores() != null) {
            var doresEntity = entity.getDetalhesPedidoAtendimento().getDores();

            doresEntity.forEach(itemRegiaoEscalaDor -> {
                var regiaoDor = ScoreRegiaoDorEnum.obterRegiaoDor(itemRegiaoEscalaDor.getCodigoRegiaoDor());
                var escalaDor = ScoreEscalaDeDorDoPacienteEnum.obterEscalaDeDor(itemRegiaoEscalaDor.getCodigoEscalaDor());

                responseDto.getDetalhesPedidoAtendimento().getDores().add(new DorDefaultResponseDTO(regiaoDor, escalaDor));
            });
        }

        if (entity.getDetalhesPedidoAtendimento().getSintomas() != null) {
            var sintomasEntity = entity.getDetalhesPedidoAtendimento().getSintomas();

            sintomasEntity.forEach(itemEntity -> {
                var idEntity = itemEntity.getId();
                var descricao = itemEntity.getDescricao();

                responseDto.getDetalhesPedidoAtendimento().getSintomas().add(new SintomaEmergenciaDefaultResponsetDTO(idEntity, descricao));
            });
        }


        if (entity.getDetalhesPedidoAtendimento().getHabitosPaciente() != null) {
            var habitosEntity = entity.getDetalhesPedidoAtendimento().getHabitosPaciente();

            habitosEntity.forEach(itemEntity -> {
                var idEntity = itemEntity.getId();
                var descricao = itemEntity.getDescricao();

                responseDto.getDetalhesPedidoAtendimento().getHabitosPaciente().add(new HabitoPacienteEmergenciaDefaultResponsetDTO(idEntity, descricao));
            });
        }

        if (entity.getDetalhesPedidoAtendimento().getEventosTraumaticos() != null) {
            var eventostraumaticosEntity = entity.getDetalhesPedidoAtendimento().getEventosTraumaticos();

            eventostraumaticosEntity.forEach(itemEntity -> {
                var idEntity = itemEntity.getId();
                var descricao = itemEntity.getDescricao();

                responseDto.getDetalhesPedidoAtendimento().getEventosTraumaticos().add(new EventoTraumaticoEmergenciaDefaultResponsetDTO(idEntity, descricao));
            });
        }

        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }

    public void populaTabelaDeDor() {
        String logCode = "populaTabelaDeDor()";
        logger.info("{} - percorrendo enum ScoreRegiaoDor {}", logCode, ScoreRegiaoDorEnum.values());

        for (ScoreRegiaoDorEnum itemRegiaoDor : ScoreRegiaoDorEnum.values()) {
            for (ScoreEscalaDeDorDoPacienteEnum itemEscalaDor : ScoreEscalaDeDorDoPacienteEnum.values()) {

                var codigoRegiaoDor = itemRegiaoDor.getCodigoRegiaoDor();
                var codigoEscalaDor = itemEscalaDor.getCodigoEscalaDeDor();

                var objOptional = regiaoDorEscalaRepository.findRegiaoDorEscalaByCodigoRegiaoDorAndCodigoEscalaDor(codigoRegiaoDor, codigoEscalaDor);

                if (!objOptional.isPresent()) {
                    var entityRegiaoDorEscala = new RegiaoDorEscala();
                    entityRegiaoDorEscala.setCodigoRegiaoDor(codigoRegiaoDor);
                    entityRegiaoDorEscala.setCodigoEscalaDor(codigoEscalaDor);
                    regiaoDorEscalaRepository.save(entityRegiaoDorEscala);
                }
            }
        }

        logger.info("{} - tabela de regiao vs escala de dor populada com sucesso {}", logCode, ScoreRegiaoDorEnum.values());
    }

}
