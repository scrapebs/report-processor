package com.sinkovdenis.reportprocessor.configuration;

import com.sinkovdenis.reportprocessor.GenericNoKafkaTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MailConfigurationTest extends GenericNoKafkaTest {
    
    @Autowired
    private MailConfiguration mailConfiguration;
    
    @Test
    public void testConfiguration() {
        assertThat(mailConfiguration.getUsername()).isNotBlank();
        assertThat(mailConfiguration.getPassword()).isNotBlank();
        assertThat(mailConfiguration.getHost()).isNotBlank();
        assertThat(mailConfiguration.getPort()).isPositive();
        assertThat(mailConfiguration.getProtocol()).isNotBlank();
        assertThat(mailConfiguration.getSsl()).isNotBlank();
    }
}
