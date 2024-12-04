package cl.faceledev.xpc.task;

import cl.faceledev.xpc.service.LoadScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class LoadScheduled {
    @Autowired
    private LoadScheduledService loadScheduledService;

    @Scheduled(cron = "${cron.expression}")
    public void loadScheduled() {
        loadScheduledService.procesaExcepcionesTask();
    }


}
