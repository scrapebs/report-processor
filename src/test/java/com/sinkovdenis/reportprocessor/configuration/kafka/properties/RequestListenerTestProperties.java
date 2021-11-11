package com.sinkovdenis.reportprocessor.configuration.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.validation.constraints.NotBlank;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Getter
@Setter
@Configuration
@Profile("!kafka_off")
public class RequestListenerTestProperties {

    public static final String LISTENER_TOPIC = "test_topic";
    public static final String LISTENER_GROUP = "test_groupId";
    public static final String NON_LISTENER_GROUP = LISTENER_GROUP + "_other";
    public static final String CLIENT_PREFIX = "test-client-id";

    @Bean
    @Primary
    public RequestListenerProperties mockRequestListenerProperties() {
        RequestListenerProperties mocked = mock(RequestListenerProperties.class);
        when(mocked.getTopic()).thenReturn(LISTENER_TOPIC);
        when(mocked.getGroupId()).thenReturn(LISTENER_GROUP);
        when(mocked.getClientIdPrefix()).thenReturn(CLIENT_PREFIX);
        return mocked;
    }
}
