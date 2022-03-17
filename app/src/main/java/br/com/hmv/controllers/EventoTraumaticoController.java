package br.com.hmv.controllers;

import br.com.hmv.dtos.request.EventoTraumaticoRequestDTO;
import br.com.hmv.dtos.request.EventoTraumaticoUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.EventoTraumaticoDefaultResponseDTO;
import br.com.hmv.services.EventoTraumaticoService;
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
@RequestMapping(value = "api/eventos_traumaticos")
@AllArgsConstructor
public class EventoTraumaticoController {
    private static Logger logger = LoggerFactory.getLogger(EventoTraumaticoController.class);
    private EventoTraumaticoService service;

    @PostMapping
    public ResponseEntity<EventoTraumaticoDefaultResponseDTO> insert(@RequestBody @Valid EventoTraumaticoRequestDTO requestDTO) {
        String logCode = "insert(EventoTraumaticoRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, requestDTO);

        var responseDTO = service.criacao(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();

        logger.info("{} - solicitacao de inclusao concluida com sucesso {}", logCode, responseDTO);
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PatchMapping(value = "/{id}/score")
    public ResponseEntity<EventoTraumaticoDefaultResponseDTO> updateScore(@PathVariable Long id, @RequestBody @Valid EventoTraumaticoUpdateScoreRequestDTO requestDTO) {
        String logCode = "updateScore(Long, EventoTraumaticoUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de dados {}", logCode, requestDTO);

        EventoTraumaticoDefaultResponseDTO responseDTO = service.updateScore(id, requestDTO);

        logger.info("{} - solicitacao de atualizacao concluida com sucesso {}", logCode, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventoTraumaticoDefaultResponseDTO> findById(@PathVariable Long id) {
        String logCode = "findById(Long)";
        logger.info("{} - solicitacao de consulta detalhe {}", logCode, id);

        EventoTraumaticoDefaultResponseDTO responseDTO = service.findByIdEventoTraumatico(id);

        logger.info("{} - solicitacao de consulta detalhe realizada com sucesso {}", logCode, responseDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<EventoTraumaticoDefaultResponseDTO>> findAll(Pageable pageable) {
        String logCode = "findAll(Pageable)";
        logger.info("{} - solicitacao de consulta todos paginada {}", logCode, pageable);

        Page<EventoTraumaticoDefaultResponseDTO> responseDtoInList = service.findAllPaged(pageable);

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
