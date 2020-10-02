package io.woolford;

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
     * Generates an audit message every second
     */
    @Scheduled(fixedDelay = 1000L)
    void logSomeStuff() {

        int index = count.getAndIncrement();        
        
        // If we exposed log4j2 instead of Slf4j then we could have used ObjectMessage

        // We want the body of audit messages to by JSON and match exactly what we want
        // this works because our pattern string in log4j2.xml is just %message
        Map foo = new HashMap();
        foo.put("message","Audit message generated");
        foo.put("eventIndex",index);
        foo.put("eventTimestamp", Instant.now().toEpochMilli());
        try {
            String jsonString = new ObjectMapper().writeValueAsString(foo);
            logger.info(AuditMarker.getMarker(), jsonString);
        } catch (JsonProcessingException e){
            // should never happen in this example
            logger.error("impossible error ",e);
        }
        
    }


}
