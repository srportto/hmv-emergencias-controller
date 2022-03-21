package br.com.hmv.controllers;

import br.com.hmv.dtos.request.EmergenciaInsertRequestDTO;
import br.com.hmv.dtos.responses.EmergenciaDefaultResponseDTO;
import br.com.hmv.services.EmergenciaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
