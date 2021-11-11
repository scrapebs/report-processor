package com.sinkovdenis.reportprocessor.configuration.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kafka.consumer")
public class RequestListenerProperties {

    @NotBlank
    private String topic;
    @NotBlank
    private String groupId;
    @NotBlank
    private String clientIdPrefix;
}
