package br.com.hmv.scheduled;

import br.com.hmv.services.EmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final EmergenciaService emergenciaService;

    @Autowired
    public ScheduledTasks(EmergenciaService emergenciaService) {
        this.emergenciaService = emergenciaService;
        this.emergenciaService.populaTabelaDeDor();
        this.syncTabelaRegiaoEscalaDor();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void syncTabelaRegiaoEscalaDor() {
        //TODO - implementar
    }
}
