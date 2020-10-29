package io.woolford;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogMessageGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    AtomicInteger count = new AtomicInteger(1);

    /**
    * Generates a log message every second.
    * The log message is not routed to Audit Appender because no log Marker is provided
    */
    @Scheduled(fixedDelay = 1000L)
    void logSomeStuff() {

        int index = count.getAndIncrement();
        logger.info("Log message generated " + index);
    }

}
