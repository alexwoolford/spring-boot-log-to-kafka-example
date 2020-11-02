package io.woolford;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeBomb {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    AtomicInteger count = new AtomicInteger(1);

    @Scheduled(fixedDelay = 1000L)
    void wait10SecondsExitApplication() {

        logger.error("Sample error message " + count.getAndIncrement());
        try {
            logger.info("Will exit in 10");
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            logger.info("Closing application");
            System.exit(0);
        }

    }

}
