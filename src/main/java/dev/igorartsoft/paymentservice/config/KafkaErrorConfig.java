package dev.igorartsoft.paymentservice.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorConfig {

    private static final Logger log = LoggerFactory.getLogger(KafkaErrorConfig.class);

    @Bean
    public CommonErrorHandler commonErrorHandler() {
        return new DefaultErrorHandler(
                (ConsumerRecord<?, ?> record, Exception exception) -> {
                    log.error(
                            "PAYMENT_STAGE_0_KAFKA_PROCESSING_FAILED topic={} partition={} offset={} key={} value={}",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value(),
                            exception
                    );
                },
                new FixedBackOff(1000L, 2L)
        );
    }
}