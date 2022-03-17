package br.com.hmv.services;

import br.com.hmv.dtos.request.EventoTraumaticoRequestDTO;
import br.com.hmv.dtos.request.EventoTraumaticoUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.EventoTraumaticoDefaultResponseDTO;
import br.com.hmv.exceptions.DatabaseException;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.EventoTraumatico;
import br.com.hmv.models.mappers.EventoTraumaticoMapper;
import br.com.hmv.repositories.EventoTraumaticoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoTraumaticoService {
    private static Logger logger = LoggerFactory.getLogger(EventoTraumaticoService.class);
    private EventoTraumaticoRepository eventoTraumaticoRepository;

    @Transactional
    public EventoTraumaticoDefaultResponseDTO criacao(EventoTraumaticoRequestDTO dto) {
        String logCode = "criacao(EventoTraumaticoRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = eventoTraumaticoRepository.save(entity);

        logger.info("{} - recurso incluido com sucesso {}", logCode, entity);
        return entityToResponseDtoInsert(entity);
    }

    @Transactional
    public EventoTraumaticoDefaultResponseDTO updateScore(Long idEventoTraumatico, EventoTraumaticoUpdateScoreRequestDTO dto) {
        String logCode = "updateScore(Long, EventoTraumaticoUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de score do EventoTraumatico {}", logCode, dto);

        try {
            var objOptional = eventoTraumaticoRepository.findById(idEventoTraumatico);
            EventoTraumatico entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idEventoTraumatico));

            entity.setScore(dto.getScore());
            var entityAtualizada = eventoTraumaticoRepository.save(entity);

            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entityAtualizada);
            return EventoTraumaticoMapper.INSTANCE.deEntityParaDto(entityAtualizada);

        } catch (EntityNotFoundException e) {
            logger.warn("{} - recurso nao encontrado id: {} ", idEventoTraumatico);
            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idEventoTraumatico);
        }
    }

    @Transactional(readOnly = true)
    public Page<EventoTraumaticoDefaultResponseDTO> findAllPaged(Pageable pageable) {
        String logCode = "findAllPaged(Pageable)";
        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);

        Page<EventoTraumatico> list = eventoTraumaticoRepository.findAll(pageable);
        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
        return list.map(itemFuncionarioEntity -> EventoTraumaticoMapper.INSTANCE.deEntityParaDto(itemFuncionarioEntity));
    }

    @Transactional(readOnly = true)
    public EventoTraumaticoDefaultResponseDTO findByIdEventoTraumatico(Long idEventoTraumatico) {
        String logCode = "findByIdEventoTraumatico(Long)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, idEventoTraumatico);

        Optional<EventoTraumatico> obj = eventoTraumaticoRepository.findById(idEventoTraumatico);
        EventoTraumatico entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idEventoTraumatico));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return EventoTraumaticoMapper.INSTANCE.deEntityParaDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        String logCode = "delete(Long)";
        logger.info("{} - deletando recurso: {}", logCode, id);

        try {
            eventoTraumaticoRepository.deleteById(id);
            logger.info("{} - recurso deletado com sucesso: {}", logCode, id);

        } catch (EmptyResultDataAccessException e) {
            logger.warn("{} - recurso nao encontrado: {}", logCode, id);
            throw new ResourceNotFoundException("Convenio nao encontrado id: " + id);

        } catch (DataIntegrityViolationException e) {
            logger.warn("{} - erro de integridade de dados: {}", logCode, id);
            throw new DatabaseException("Integrity violation - Ao deletar convenio id: " + id);

        } catch (Exception e) {
            logger.warn("{} - erro ao processar requisicao: {}", logCode, id);
            throw new DatabaseException(e.getMessage());
        }
    }

    private EventoTraumatico dtoToEntityOnCreate(EventoTraumaticoRequestDTO dto) {
        String logCode = "dtoToEntityOnCreate(EventoTraumaticoRequestDTO)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        var entity = EventoTraumaticoMapper.INSTANCE.deDtoParaEntity(dto);

        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
        return entity;
    }

    private EventoTraumaticoDefaultResponseDTO entityToResponseDtoInsert(EventoTraumatico entity) {
        String logCode = "entityToResponseDefault(EventoTraumaticos)";
        logger.info("{} - convertendo entity para response default {}", logCode, entity);

        var responseDto = EventoTraumaticoMapper.INSTANCE.deEntityParaDto(entity);
        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }

}
