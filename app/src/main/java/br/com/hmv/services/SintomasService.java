package br.com.hmv.services;

import br.com.hmv.dtos.request.SintomaRequestDTO;
import br.com.hmv.dtos.request.SintomaUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.SintomaDefaultResponseDTO;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Sintomas;
import br.com.hmv.models.mappers.SintomasMapper;
import br.com.hmv.repositories.SintomasRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SintomasService {
    private static Logger logger = LoggerFactory.getLogger(SintomasService.class);
    private SintomasRepository sintomasRepository;

    @Transactional
    public SintomaDefaultResponseDTO criacao(SintomaRequestDTO dto) {
        String logCode = "criacao(SintomaRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = sintomasRepository.save(entity);

        logger.info("{} - Convenio incluido com sucesso {}", logCode, entity);
        return entityToResponseDtoInsert(entity);
    }

    @Transactional
    public SintomaDefaultResponseDTO updateScore(Long idSintoma, SintomaUpdateScoreRequestDTO dto) {
        String logCode = "updateScore(String, SintomaUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de score do sintoma {}", logCode, dto);

        try {
            var objOptional = sintomasRepository.findById(idSintoma);
            Sintomas entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idSintoma));

            entity.setScore(dto.getScore());
            var entityAtualizada = sintomasRepository.save(entity);

            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entityAtualizada);
            return SintomasMapper.INSTANCE.deEntityParaDto(entityAtualizada);

        } catch (EntityNotFoundException e) {
            logger.warn("{} - recurso nao encontrado id: {} ", idSintoma);
            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idSintoma);
        }
    }


    @Transactional(readOnly = true)
    public Page<SintomaDefaultResponseDTO> findAllPaged(Pageable pageable) {
        String logCode = "findAllPaged(Pageable)";
        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);

        Page<Sintomas> list = sintomasRepository.findAll(pageable);
        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
        return list.map(itemFuncionarioEntity -> SintomasMapper.INSTANCE.deEntityParaDto(itemFuncionarioEntity));
    }


    @Transactional(readOnly = true)
    public SintomaDefaultResponseDTO findByIdSintoma(Long idSintoma) {
        String logCode = "findByIdSintoma(Long)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, idSintoma);

        Optional<Sintomas> obj = sintomasRepository.findById(idSintoma);
        Sintomas entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idSintoma));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return SintomasMapper.INSTANCE.deEntityParaDto(entity);
    }

    private Sintomas dtoToEntityOnCreate(SintomaRequestDTO dto) {
        String logCode = "dtoToEntityOnCreate(SintomaRequestDTO)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        var entity = SintomasMapper.INSTANCE.deDtoParaEntity(dto);

        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
        return entity;
    }

    private SintomaDefaultResponseDTO entityToResponseDtoInsert(Sintomas entity) {
        String logCode = "entityToResponseDefault(Sintomas)";
        logger.info("{} - convertendo entity para response default {}", logCode, entity);

        var responseDto = SintomasMapper.INSTANCE.deEntityParaDto(entity);
        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }

}