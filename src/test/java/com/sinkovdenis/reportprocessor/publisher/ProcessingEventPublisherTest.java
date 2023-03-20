package com.sinkovdenis.reportprocessor.publisher;

import com.sinkovdenis.reportprocessor.configuration.kafka.properties.KafkaAdditionalHeaders;
import com.sinkovdenis.reportprocessor.configuration.kafka.properties.ProcessingEventPublisherProperties;
import com.sinkovdenis.reportprocessor.model.ProcessingEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProcessingEventPublisherTest {
    
    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Mock
    private ProcessingEventPublisherProperties properties;

    @Mock
    private ProcessingEvent processingEvent;

    @Spy
    @InjectMocks
    private ProcessingEventPublisher publisher;

    @Test
    void testPublish() {
        publisher.publish(processingEvent);
        verify(kafkaTemplate).executeInTransaction(any());
    }

    @Test
    void testPublish_null() {
        assertThat(catchThrowableOfType(() -> publisher.publish(null),
                NullPointerException.class)).isNotNull();
    }

    @Test
    void testBuildMessage() {
        doReturn("name").when(properties).getSenderName();
        doReturn("id").when(properties).getSenderId();
        Message<ProcessingEvent> message = publisher.buildMessage(processingEvent);
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.SENDER_NAME)).isEqualTo("name");
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.SENDER_ID)).isEqualTo("id");
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.MESSAGE_ID).toString()).isNotEmpty();
    }

    @Test
    void testBuildMessage_null() {
        assertThat(catchThrowableOfType(() -> publisher.buildMessage(null),
                NullPointerException.class)).isNotNull();
    }
}
