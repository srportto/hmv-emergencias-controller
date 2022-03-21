package br.com.hmv.services;

import br.com.hmv.dtos.request.EmergenciaInsertRequestDTO;
import br.com.hmv.dtos.responses.EmergenciaDefaultResponseDTO;
import br.com.hmv.models.entities.Dor;
import br.com.hmv.models.entities.Emergencia;
import br.com.hmv.models.mappers.EmergenciaMapper;
import br.com.hmv.repositories.EmergenciaRepository;
import br.com.hmv.repositories.EventoTraumaticoRepository;
import br.com.hmv.repositories.HabitoPacienteRepository;
import br.com.hmv.repositories.SintomaRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmergenciaService {
    private static Logger logger = LoggerFactory.getLogger(EmergenciaService.class);
    private EmergenciaRepository emergenciaRepository;
    private SintomaRepository sintomaRepository;
    private HabitoPacienteRepository habitoPacienteRepository;
    private EventoTraumaticoRepository eventoTraumaticoRepository;

    @Transactional
    public EmergenciaDefaultResponseDTO criacao(EmergenciaInsertRequestDTO dto) {
        String logCode = "criacao(SintomaRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = emergenciaRepository.save(entity);

        logger.info("{} - recurso incluido com sucesso {}", logCode, entity);
        return entityToDefaultResponseDto(entity);
    }

//    @Transactional
//    public SintomaDefaultResponseDTO updateScore(Long idSintoma, SintomaUpdateScoreRequestDTO dto) {
//        String logCode = "updateScore(String, SintomaUpdateScoreRequestDTO)";
//        logger.info("{} - solicitacao de atualizacao de score do sintoma {}", logCode, dto);
//
//        try {
//            var objOptional = sintomaRepository.findById(idSintoma);
//            Sintoma entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idSintoma));
//
//            entity.setScore(dto.getScore());
//            var entityAtualizada = sintomaRepository.save(entity);
//
//            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entityAtualizada);
//            return SintomaMapper.INSTANCE.deEntityParaDto(entityAtualizada);
//
//        } catch (EntityNotFoundException e) {
//            logger.warn("{} - recurso nao encontrado id: {} ", idSintoma);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idSintoma);
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
//    @Transactional(readOnly = true)
//    public SintomaDefaultResponseDTO findByIdSintoma(Long idSintoma) {
//        String logCode = "findByIdSintoma(Long)";
//        logger.info("{} - buscando recurso pelo id: {}", logCode, idSintoma);
//
//        Optional<Sintoma> obj = sintomaRepository.findById(idSintoma);
//        Sintoma entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idSintoma));
//
//        logger.info("{} - recurso encontrado: {}", logCode, entity);
//        return SintomaMapper.INSTANCE.deEntityParaDto(entity);
//    }
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
        entity.getDetalhesPedidoAtendimento().setCodigoDetalhesPedido(UUID.randomUUID().toString());
        entity.setCodigoStatusEmergencia(dto.getStatusEmergencia().getCodigoStatusEmergencia());
        entity.setScore(9999);
        var detalhesPedidoAtendimento = dto.getDetalhesPedidoAtendimento();

        if (detalhesPedidoAtendimento.getDores() != null) {
            var dores = detalhesPedidoAtendimento.getDores();
            entity.getDetalhesPedidoAtendimento().getDores().clear();

            dores.forEach(itemDores -> {
                var dor = new Dor();
                dor.setCodigoRegiaoDor(itemDores.getRegiaoDor().getCodigoRegiaoDor());
                dor.setCodigoEscalaDor(itemDores.getEscalaDeDorDoPaciente().getCodigoEscalaDeDor());

                entity.getDetalhesPedidoAtendimento().getDores().add(dor);
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
        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }

}
