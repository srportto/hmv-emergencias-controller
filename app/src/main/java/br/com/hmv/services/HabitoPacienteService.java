package br.com.hmv.services;

import br.com.hmv.dtos.request.HabitoPacienteRequestDTO;
import br.com.hmv.dtos.request.HabitoPacienteUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.HabitoPacienteDefaultResponseDTO;
import br.com.hmv.exceptions.DatabaseException;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.HabitoPaciente;
import br.com.hmv.models.mappers.HabitoPacienteMapper;
import br.com.hmv.repositories.HabitoPacienteRepository;
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
public class HabitoPacienteService {
    private static Logger logger = LoggerFactory.getLogger(HabitoPacienteService.class);
    private HabitoPacienteRepository HabitoPacienteRepository;

    @Transactional
    public HabitoPacienteDefaultResponseDTO criacao(HabitoPacienteRequestDTO dto) {
        String logCode = "criacao(HabitoPacienteRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = HabitoPacienteRepository.save(entity);

        logger.info("{} - recurso incluido com sucesso {}", logCode, entity);
        return entityToResponseDtoInsert(entity);
    }

    @Transactional
    public HabitoPacienteDefaultResponseDTO updateScore(Long idHabitoPaciente, HabitoPacienteUpdateScoreRequestDTO dto) {
        String logCode = "updateScore(Long, HabitoPacienteUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de score do HabitoPaciente {}", logCode, dto);

        try {
            var objOptional = HabitoPacienteRepository.findById(idHabitoPaciente);
            HabitoPaciente entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idHabitoPaciente));

            entity.setScore(dto.getScore());
            var entityAtualizada = HabitoPacienteRepository.save(entity);

            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entityAtualizada);
            return HabitoPacienteMapper.INSTANCE.deEntityParaDto(entityAtualizada);

        } catch (EntityNotFoundException e) {
            logger.warn("{} - recurso nao encontrado id: {} ", idHabitoPaciente);
            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idHabitoPaciente);
        }
    }

    @Transactional(readOnly = true)
    public Page<HabitoPacienteDefaultResponseDTO> findAllPaged(Pageable pageable) {
        String logCode = "findAllPaged(Pageable)";
        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);

        Page<HabitoPaciente> list = HabitoPacienteRepository.findAll(pageable);
        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
        return list.map(itemFuncionarioEntity -> HabitoPacienteMapper.INSTANCE.deEntityParaDto(itemFuncionarioEntity));
    }

    @Transactional(readOnly = true)
    public HabitoPacienteDefaultResponseDTO findByIdHabitoPaciente(Long idHabitoPaciente) {
        String logCode = "findByIdHabitoPaciente(Long)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, idHabitoPaciente);

        Optional<HabitoPaciente> obj = HabitoPacienteRepository.findById(idHabitoPaciente);
        HabitoPaciente entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idHabitoPaciente));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return HabitoPacienteMapper.INSTANCE.deEntityParaDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        String logCode = "delete(Long)";
        logger.info("{} - deletando recurso: {}", logCode, id);

        try {
            HabitoPacienteRepository.deleteById(id);
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

    private HabitoPaciente dtoToEntityOnCreate(HabitoPacienteRequestDTO dto) {
        String logCode = "dtoToEntityOnCreate(HabitoPacienteRequestDTO)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        var entity = HabitoPacienteMapper.INSTANCE.deDtoParaEntity(dto);

        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
        return entity;
    }

    private HabitoPacienteDefaultResponseDTO entityToResponseDtoInsert(HabitoPaciente entity) {
        String logCode = "entityToResponseDefault(HabitoPacientes)";
        logger.info("{} - convertendo entity para response default {}", logCode, entity);

        var responseDto = HabitoPacienteMapper.INSTANCE.deEntityParaDto(entity);
        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }

}
