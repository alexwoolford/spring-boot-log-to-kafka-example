package io.woolford.audit;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * log4j2.xml configures Kafka Audit Appender to only forward messages with this
 * logging marker.
 */
public class AuditMarker {

    protected static Marker getMarker() {
        return MarkerFactory.getMarker("AuditRecord");
    }
}
