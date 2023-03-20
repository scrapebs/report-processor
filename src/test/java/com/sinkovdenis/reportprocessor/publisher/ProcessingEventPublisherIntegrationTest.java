package com.sinkovdenis.reportprocessor.publisher;

import com.sinkovdenis.reportprocessor.GenericTest;
import com.sinkovdenis.reportprocessor.TestSinglePartitionTopicHelper;
import com.sinkovdenis.reportprocessor.model.ProcessingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.sinkovdenis.reportprocessor.TestData.createProcessingEvent;
import static com.sinkovdenis.reportprocessor.publisher.ProcessingEventPublisherTestParams.ANY_GROUP;
import static com.sinkovdenis.reportprocessor.publisher.ProcessingEventPublisherTestParams.PROCESSING_EVENTS_TOPIC;

@EmbeddedKafka(
        partitions = 1,
        topics = {PROCESSING_EVENTS_TOPIC},
        brokerProperties = {
                "transaction.state.log.replication.factor=1",
                "transaction.state.log.min.isr=1"
        }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProcessingEventPublisherIntegrationTest extends GenericTest {

    @SpyBean
    private ProcessingEventPublisher publisher;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private TestSinglePartitionTopicHelper helper;

    private static ProcessingEvent TEST_PROCESSING_EVENT;

    @BeforeEach
    public void setUp() {
        helper = new TestSinglePartitionTopicHelper(
                embeddedKafkaBroker,
                TimeUnit.SECONDS.toMillis(1)
        );
        helper.waitListeners(kafkaListenerEndpointRegistry);

        TEST_PROCESSING_EVENT = createProcessingEvent();
    }

    @Test
    void testPublish() throws Exception {
        helper.assertEmpty(PROCESSING_EVENTS_TOPIC, ANY_GROUP);

        publisher.publish(TEST_PROCESSING_EVENT);

        TimeUnit.SECONDS.sleep(5);
        helper.assertOne(PROCESSING_EVENTS_TOPIC, ANY_GROUP);
    }
}
