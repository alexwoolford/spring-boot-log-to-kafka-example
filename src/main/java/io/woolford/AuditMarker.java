package io.woolford;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * log4j2.xml configures Kafka Audit Appender to only forward messages with this logging marker.
 */
public class AuditMarker  {

    protected static Marker getMarker(){
        return MarkerFactory.getMarker("AuditRecord");
    }
}
