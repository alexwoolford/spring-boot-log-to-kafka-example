package io.woolford.audit;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AuditMessageGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    AtomicInteger count = new AtomicInteger(1);

    /**
     * Generates a log message every second.
     * The log message is routed to Audit Appender via the use of a log Marker
     */
    @Scheduled(fixedDelay = 1000L)
    void logSomeStuff() {

        int index = count.getAndIncrement();

        // If we exposed log4j2 instead of Slf4j then we could have used ObjectMessage
        String jsonString = AuditMessage.builder()
            .message("Event Message Generated")
            .index(index)
            .eventTimestamp(Instant.now())
            .build()
            .toJSON();
        
        // show audit and regular audit based on the presence of Audit Marker
        logger.info(AuditMarker.getMarker(), jsonString);
    }
}
