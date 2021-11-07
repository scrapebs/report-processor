package com.sinkovdenis.reportprocessor.listener;

import com.sinkovdenis.reportprocessor.component.EmailSender;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessorResolver;
import com.sinkovdenis.reportprocessor.configuration.kafka.properties.RequestListenerProperties;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    private final ReportProcessorResolver reportProcessorResolver;

    private final EmailSender emailSender;

    @KafkaHandler
    public <R extends GenericReportRequest> void handle(@Payload R request) {
        Optional<ReportProcessor<R>> reportProcessor = reportProcessorResolver.resolveBy(request);
        if (reportProcessor.isPresent()) {
            Report report = reportProcessor.get().process(request);
            emailSender.sendReport(report);
        } else {
            throw new IllegalArgumentException("Failed to find suitable processor for reportType" + request.getReportType());
        }
    }
}
