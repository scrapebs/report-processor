package com.sinkovdenis.reportprocessor.configuration.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Validated
@Configuration
@ConfigurationProperties(prefix = "kafka.producer")
public class ProcessingEventPublisherProperties {

    @NotBlank
    private String topic;
    @NotBlank
    private String senderId;
    @NotBlank
    private String senderName;
}
