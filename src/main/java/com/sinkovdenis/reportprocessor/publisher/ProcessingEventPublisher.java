package com.sinkovdenis.reportprocessor.publisher;

import com.sinkovdenis.reportprocessor.configuration.kafka.properties.KafkaAdditionalHeaders;
import com.sinkovdenis.reportprocessor.configuration.kafka.properties.ProcessingEventPublisherProperties;
import com.sinkovdenis.reportprocessor.model.ProcessingEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProcessingEventPublisher {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final ProcessingEventPublisherProperties properties;
    
    public void publish(@NonNull ProcessingEvent event) {
        kafkaTemplate.executeInTransaction(operation -> operation.send(buildMessage(event)));
    }

    Message<ProcessingEvent> buildMessage(@NonNull ProcessingEvent event) {
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, properties.getTopic())
                .setHeader(KafkaAdditionalHeaders.SENDER_ID, properties.getSenderId())
                .setHeader(KafkaAdditionalHeaders.SENDER_NAME, properties.getSenderName())
                .setHeader(KafkaAdditionalHeaders.MESSAGE_ID, UUID.randomUUID().toString())
                .build();
    }
       
}
