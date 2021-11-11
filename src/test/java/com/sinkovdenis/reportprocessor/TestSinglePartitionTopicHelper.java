package com.sinkovdenis.reportprocessor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class TestSinglePartitionTopicHelper {

    private final EmbeddedKafkaBroker embeddedKafkaBroker;
    private final long readTimeout;

    public void waitListeners(
            KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
        kafkaListenerEndpointRegistry.getListenerContainers()
                .forEach(c -> ContainerTestUtils
                        .waitForAssignment(c, embeddedKafkaBroker.getPartitionsPerTopic()));
    }

    @SneakyThrows
    public void send(String topic, Object message) {
        KafkaTemplate<Object, Object> kafkaTemplate = nonTransactionalKafkaTemplate();

        kafkaTemplate.send(topic, "key", message);
        kafkaTemplate.flush();
    }

    public void assertOne(String topic, String group) {
        assertCount(topic, group, 1);
    }

    public void assertCount(String topic, String group, int countExpected) {
        assertThat(waitRecords(topic, group, readTimeout).count()).isEqualTo(countExpected);
    }

    public void assertNotEmpty(String topic, String group) {
        assertThat(waitRecords(topic, group, readTimeout).isEmpty()).isFalse();
    }

    public void assertEmpty(String topic, String group) {
        assertThat(waitRecords(topic, group, readTimeout).isEmpty()).isTrue();
    }

    public <K, V> ConsumerRecords<K, V> waitRecords(String topic, String groupId, long timeout) {
        try (Consumer<K, V> consumer = consumer(groupId)) {
            embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, topic);

            return KafkaTestUtils.getRecords(consumer, timeout);
        }
    }

    private KafkaTemplate<Object, Object> nonTransactionalKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    private <K, V> Consumer<K, V> consumer(String groupId) {
        return ((ConsumerFactory<K, V>) consumerFactory()).createConsumer(groupId, groupId + "-client");
    }

    private <K, V> ConsumerFactory<K, V> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

        return new DefaultKafkaConsumerFactory<K, V>(props);
    }
}
