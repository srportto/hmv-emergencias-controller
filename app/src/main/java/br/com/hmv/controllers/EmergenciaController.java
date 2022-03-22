package br.com.hmv.controllers;

import br.com.hmv.dtos.request.EmergenciaInsertRequestDTO;
import br.com.hmv.dtos.responses.EmergenciaDefaultResponseDTO;
import br.com.hmv.dtos.responses.EmergenciaForListResponseDTO;
import br.com.hmv.models.enums.StatusEmergenciaEnum;
import br.com.hmv.services.EmergenciaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "api/emergencias")
@AllArgsConstructor
public class EmergenciaController {
    private static Logger logger = LoggerFactory.getLogger(EmergenciaController.class);
    private EmergenciaService service;

    @PostMapping
    public ResponseEntity<EmergenciaDefaultResponseDTO> insert(@RequestBody @Valid EmergenciaInsertRequestDTO requestDTO) {
        String logCode = "insert(EmergenciaInsertRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, requestDTO);

        var responseDTO = service.criacao(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getCodigoEmergencia()).toUri();

        logger.info("{} - solicitacao de inclusao concluida com sucesso {}", logCode, responseDTO);
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmergenciaDefaultResponseDTO> findById(@PathVariable String id) {
        String logCode = "findById(String)";
        logger.info("{} - solicitacao de consulta detalhe {}", logCode, id);

        EmergenciaDefaultResponseDTO responseDTO = service.findByCodigoEmergencia(id);

        logger.info("{} - solicitacao de consulta detalhe realizada com sucesso {}", logCode, responseDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<EmergenciaForListResponseDTO>> findAll(Pageable pageable) {
        String logCode = "findAll(Pageable)";
        logger.info("{} - solicitacao de consulta todos paginada {} ", logCode, pageable);

        Page<EmergenciaForListResponseDTO> responseDtoInList = service.findAllPaged(pageable);

        logger.info("{} - solicitacao de consulta todos paginada realizada com sucesso{}", logCode, pageable);
        return ResponseEntity.ok().body(responseDtoInList);
    }

    @GetMapping(value = "/status")
    public ResponseEntity<Page<EmergenciaForListResponseDTO>> findAllByStatusEmergencia(@RequestParam StatusEmergenciaEnum status, Pageable pageable) {
        String logCode = "findAllByStatusEmergencia(StatusEmergenciaEnum,Pageable)";
        logger.info("{} - solicitacao de consulta todos paginada {} por status da emergencia {}", logCode, pageable, status);

        Page<EmergenciaForListResponseDTO> responseDtoInList = service.findAllPagedPorStatusEmergencia(status, pageable);

        logger.info("{} - solicitacao de consulta todos paginada realizada com sucesso{}", logCode, pageable);
        return ResponseEntity.ok().body(responseDtoInList);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        String logCode = "delete(String)";
        logger.info("{} - solicitacao de delete {}", logCode, id);

        service.delete(id);

        logger.info("{} - solicitacao de delete realizada com sucesso {}", logCode, id);
        return ResponseEntity.noContent().build();
    }


}
