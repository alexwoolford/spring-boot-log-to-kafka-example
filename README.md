# Spring Boot: how to log to Kafka

Kafka is ideal for log aggregation, particularly for applications that use microservices and are distributed across multiple hosts.

This is an example Spring Boot application that uses [Log4j2's Kafka appender](https://logging.apache.org/log4j/2.x/manual/appenders.html#KafkaAppender) to send JSON formatted log messages to a Kafka topic.

This application runs for 10 seconds and sends some log messages to a Kafka topic that's defined in `src/main/resources/log4j2.xml`. Here's an example log message:

    {
      "timeMillis" : 1485736021946,
      "thread" : "pool-2-thread-1",
      "level" : "INFO",
      "loggerName" : "io.woolford.LogMessageGenerator",
      "message" : "Log message generated",
      "endOfBatch" : false,
      "loggerFqcn" : "org.apache.logging.slf4j.Log4jLogger",
      "threadId" : 17,
      "threadPriority" : 5
    }

Here's a two-minute video that walks through the code:
[![logging to Kafka from Spring video walk-through](https://img.youtube.com/vi/G7wMwEDkX_4/0.jpg)](https://www.youtube.com/watch?v=G7wMwEDkX_4)

To do:

1) include the host name in  the log message
