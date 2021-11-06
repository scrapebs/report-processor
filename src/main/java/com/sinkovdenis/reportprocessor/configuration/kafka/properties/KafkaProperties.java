package com.sinkovdenis.reportprocessor.configuration.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@Validated
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    @NotBlank
    private String bootstrapServers;

    private final Map<String, String> commonClientProperties = new HashMap<>();

    @Value("app(${spring.application.name}).")
    private String transactionIdPrefix;
}
