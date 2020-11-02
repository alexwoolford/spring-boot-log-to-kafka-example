package io.woolford.audit;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Lets us demo builder style fluent api as part of audit
 */
@Builder
@Slf4j
public class AuditMessage {
    
    String message;
    int index; 
    Instant eventTimestamp;

    public String toJSON(){
        // We want the body of audit messages to by JSON and match exactly what we want
        // this works because our pattern string in log4j2.xml is just %message
        Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("message", "Audit message generated");
        foo.put("eventIndex", index);
        foo.put("eventTimestamp", Instant.now().toEpochMilli());
        try {
            // Convert the Map to JSON and send the raw JSON as the message.
            // The Audit appender formatter picks that json up and pubishes just the JSON to the topic
            return new ObjectMapper().writeValueAsString(foo);
        } catch (JsonProcessingException e) {
            // should never happen in this example
            throw new RuntimeException("impossible error ", e);
        }

    }
}
