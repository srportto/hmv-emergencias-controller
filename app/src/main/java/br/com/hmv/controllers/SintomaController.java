package br.com.hmv.controllers;

import br.com.hmv.dtos.request.SintomaRequestDTO;
import br.com.hmv.dtos.request.SintomaUpdateScoreRequestDTO;
import br.com.hmv.dtos.responses.SintomaDefaultResponseDTO;
import br.com.hmv.services.SintomaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "api/sintomas")
@AllArgsConstructor
public class SintomaController {
    private static Logger logger = LoggerFactory.getLogger(SintomaController.class);
    private SintomaService service;

    @PostMapping
    public ResponseEntity<SintomaDefaultResponseDTO> insert(@RequestBody @Valid SintomaRequestDTO requestDTO) {
        String logCode = "insert(SintomaRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, requestDTO);

        var responseDTO = service.criacao(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();

        logger.info("{} - solicitacao de inclusao concluida com sucesso {}", logCode, responseDTO);
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PatchMapping(value = "/{id}/score")
    public ResponseEntity<SintomaDefaultResponseDTO> updateScore(@PathVariable Long id, @RequestBody @Valid SintomaUpdateScoreRequestDTO requestDTO) {
        String logCode = "updateScore(Long, SintomaUpdateScoreRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de dados {}", logCode, requestDTO);

        SintomaDefaultResponseDTO responseDTO = service.updateScore(id, requestDTO);

        logger.info("{} - solicitacao de atualizacao concluida com sucesso {}", logCode, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SintomaDefaultResponseDTO> findById(@PathVariable Long id) {
        String logCode = "findById(Long)";
        logger.info("{} - solicitacao de consulta detalhe {}", logCode, id);

        SintomaDefaultResponseDTO responseDTO = service.findByIdSintoma(id);

        logger.info("{} - solicitacao de consulta detalhe realizada com sucesso {}", logCode, responseDTO);
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping
    public ResponseEntity<Page<SintomaDefaultResponseDTO>> findAll(Pageable pageable) {
        String logCode = "findAll(Pageable)";
        logger.info("{} - solicitacao de consulta todos paginada {}", logCode, pageable);

        Page<SintomaDefaultResponseDTO> responseDtoInList = service.findAllPaged(pageable);

        logger.info("{} - solicitacao de consulta todos paginada realizada com sucesso{}", logCode, pageable);
        return ResponseEntity.ok().body(responseDtoInList);
    }
}
