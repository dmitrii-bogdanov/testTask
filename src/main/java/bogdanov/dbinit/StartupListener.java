package bogdanov.dbinit;

import bogdanov.dbinit.services.interfaces.NameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:table.properties")
class StartupListener {

    private final NameService nameService;
    private boolean wasInvoked = false;

    @Value("${numberOfRecordsToGenerate}")
    private int recordsNumber;
    @Value("${batchSize}")
    private int batchSize;

    @EventListener(ContextRefreshedEvent.class)
    private void contextRefreshedEvent() {
        if (!wasInvoked) {
            wasInvoked = nameService.generateAndSave(recordsNumber, batchSize);
        }
    }

}
