package com.sinkovdenis.reportprocessor.listener;

import com.sinkovdenis.reportprocessor.configuration.kafka.properties.RequestListenerProperties;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import com.sinkovdenis.reportprocessor.service.ReportProcessorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        beanRef = "listener",
        id = "#{listener.properties.groupId}",
        topics = {"#{listener.properties.topic}"},
        clientIdPrefix = "#{listener.properties.clientIdPrefix}")
public class ReportRequestListener {

    @Getter
    private final RequestListenerProperties properties;

    private final ReportProcessorService service;

    @KafkaHandler
    public <R extends GenericReportRequest> void handle(@Payload R request) {
       service.process(request);
    }
}
