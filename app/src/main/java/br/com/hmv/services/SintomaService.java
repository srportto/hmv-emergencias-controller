package br.com.hmv.services;

import br.com.hmv.dtos.request.SintomaRequestDTO;
import br.com.hmv.dtos.request.SintomaUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.SintomaDefaultResponseDTO;
import br.com.hmv.exceptions.DatabaseException;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Sintoma;
import br.com.hmv.models.mappers.SintomaMapper;
import br.com.hmv.repositories.SintomaRepository;
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
public class SintomaService {
    private static Logger logger = LoggerFactory.getLogger(SintomaService.class);
    private SintomaRepository sintomaRepository;

    @Transactional
    public SintomaDefaultResponseDTO criacao(SintomaRequestDTO dto) {
        String logCode = "criacao(SintomaRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = sintomaRepository.save(entity);

        logger.info("{} - recurso incluido com sucesso {}", logCode, entity);
        return entityToResponseDtoInsert(entity);
    }

    @Transactional
    public SintomaDefaultResponseDTO updateScore(Long idSintoma, SintomaUpdateScoreRequestDTO dto) {
        String logCode = "updateScore(String, SintomaUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de score do sintoma {}", logCode, dto);

        try {
            var objOptional = sintomaRepository.findById(idSintoma);
            Sintoma entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idSintoma));

            entity.setScore(dto.getScore());
            var entityAtualizada = sintomaRepository.save(entity);

            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entityAtualizada);
            return SintomaMapper.INSTANCE.deEntityParaDto(entityAtualizada);

        } catch (EntityNotFoundException e) {
            logger.warn("{} - recurso nao encontrado id: {} ", idSintoma);
            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idSintoma);
        }
    }


    @Transactional(readOnly = true)
    public Page<SintomaDefaultResponseDTO> findAllPaged(Pageable pageable) {
        String logCode = "findAllPaged(Pageable)";
        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);

        Page<Sintoma> list = sintomaRepository.findAll(pageable);
        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
        return list.map(itemFuncionarioEntity -> SintomaMapper.INSTANCE.deEntityParaDto(itemFuncionarioEntity));
    }

    @Transactional(readOnly = true)
    public SintomaDefaultResponseDTO findByIdSintoma(Long idSintoma) {
        String logCode = "findByIdSintoma(Long)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, idSintoma);

        Optional<Sintoma> obj = sintomaRepository.findById(idSintoma);
        Sintoma entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idSintoma));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return SintomaMapper.INSTANCE.deEntityParaDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        String logCode = "delete(Long)";
        logger.info("{} - deletando recurso: {}", logCode, id);

        try {
            sintomaRepository.deleteById(id);
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

    private Sintoma dtoToEntityOnCreate(SintomaRequestDTO dto) {
        String logCode = "dtoToEntityOnCreate(SintomaRequestDTO)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        var entity = SintomaMapper.INSTANCE.deDtoParaEntity(dto);

        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
        return entity;
    }

    private SintomaDefaultResponseDTO entityToResponseDtoInsert(Sintoma entity) {
        String logCode = "entityToResponseDefault(Sintomas)";
        logger.info("{} - convertendo entity para response default {}", logCode, entity);

        var responseDto = SintomaMapper.INSTANCE.deEntityParaDto(entity);
        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }

}
