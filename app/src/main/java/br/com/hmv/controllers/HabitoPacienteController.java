package br.com.hmv.controllers;

import br.com.hmv.dtos.request.HabitoPacienteRequestDTO;
import br.com.hmv.dtos.request.HabitoPacienteUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.HabitoPacienteDefaultResponseDTO;
import br.com.hmv.services.HabitoPacienteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "api/habitos")
@AllArgsConstructor
public class HabitoPacienteController {
    private static Logger logger = LoggerFactory.getLogger(HabitoPacienteController.class);
    private HabitoPacienteService service;

    @PostMapping
    public ResponseEntity<HabitoPacienteDefaultResponseDTO> insert(@RequestBody @Valid HabitoPacienteRequestDTO requestDTO) {
        String logCode = "insert(HabitoPacienteRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, requestDTO);

        var responseDTO = service.criacao(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();

        logger.info("{} - solicitacao de inclusao concluida com sucesso {}", logCode, responseDTO);
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PatchMapping(value = "/{id}/score")
    public ResponseEntity<HabitoPacienteDefaultResponseDTO> updateScore(@PathVariable Long id, @RequestBody @Valid HabitoPacienteUpdateScoreRequestDTO requestDTO) {
        String logCode = "updateScore(Long, HabitoPacienteUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de dados {}", logCode, requestDTO);

        HabitoPacienteDefaultResponseDTO responseDTO = service.updateScore(id, requestDTO);

        logger.info("{} - solicitacao de atualizacao concluida com sucesso {}", logCode, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HabitoPacienteDefaultResponseDTO> findById(@PathVariable Long id) {
        String logCode = "findById(Long)";
        logger.info("{} - solicitacao de consulta detalhe {}", logCode, id);

        HabitoPacienteDefaultResponseDTO responseDTO = service.findByIdHabitoPaciente(id);

        logger.info("{} - solicitacao de consulta detalhe realizada com sucesso {}", logCode, responseDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<HabitoPacienteDefaultResponseDTO>> findAll(Pageable pageable) {
        String logCode = "findAll(Pageable)";
        logger.info("{} - solicitacao de consulta todos paginada {}", logCode, pageable);

        Page<HabitoPacienteDefaultResponseDTO> responseDtoInList = service.findAllPaged(pageable);

        logger.info("{} - solicitacao de consulta todos paginada realizada com sucesso{}", logCode, pageable);
        return ResponseEntity.ok().body(responseDtoInList);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String logCode = "delete(Long)";
        logger.info("{} - solicitacao de delete {}", logCode, id);

        service.delete(id);

        logger.info("{} - solicitacao de delete realizada com sucesso {}", logCode, id);
        return ResponseEntity.noContent().build();
    }
}
