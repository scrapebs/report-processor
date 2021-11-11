package com.sinkovdenis.reportprocessor.configuration.kafka.properties;

import com.sinkovdenis.reportprocessor.GenericNoKafkaTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestListenerPropertiesTest extends GenericNoKafkaTest {

    @Autowired
    private RequestListenerProperties properties;

    @Test
    public void testConfigurationProperties() {
        assertThat(properties.getTopic()).isNotBlank();
        assertThat(properties.getGroupId()).isNotBlank();
        assertThat(properties.getClientIdPrefix()).isNotBlank();
    }
}
