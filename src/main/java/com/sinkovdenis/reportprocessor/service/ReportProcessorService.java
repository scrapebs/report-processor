package com.sinkovdenis.reportprocessor.service;

import com.sinkovdenis.reportprocessor.component.ReportResultsSender;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessorResolver;
import com.sinkovdenis.reportprocessor.model.ProcessingEvent;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import com.sinkovdenis.reportprocessor.publisher.ProcessingEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sinkovdenis.reportprocessor.model.ProcessingEvent.ProcessingEventStatus.ERROR;
import static com.sinkovdenis.reportprocessor.model.ProcessingEvent.ProcessingEventStatus.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportProcessorService {

    private final ReportProcessorResolver reportProcessorResolver;
    private final ReportResultsSender reportResultsSender;
    private final ProcessingEventPublisher processingEventPublisher;

    public <R extends GenericReportRequest> void process(R request) {
        try {
            Optional<ReportProcessor<R>> reportProcessor = reportProcessorResolver.resolveBy(request);
            if (reportProcessor.isPresent()) {
                Report report = reportProcessor.get().process(request);
                reportResultsSender.sendReport(report);
                processingEventPublisher.publish(buildProcessingEvent(request.getRequestId(), SUCCESS));
            } else {
                processingEventPublisher.publish(buildProcessingEvent(request.getRequestId(), ERROR,
                        String.format("request type(%s) (%s) is not supported", request.getClass().getName(), request.getReportType())));
            }
        } catch (Exception e) {
            log.warn("exception", e);
            processingEventPublisher.publish(buildProcessingEvent(request.getRequestId(), ERROR, e.getMessage()));
        }
    }
    

    ProcessingEvent buildProcessingEvent(long requestId, ProcessingEvent.ProcessingEventStatus status) {
        return buildProcessingEvent(requestId, status, null);
    }
    
    ProcessingEvent buildProcessingEvent(long requestId, ProcessingEvent.ProcessingEventStatus status, String details) {
        ProcessingEvent event = new ProcessingEvent();
        event.setRequestId(requestId);
        event.setStatus(status);
        event.setDetails(details);
        return event;
    }
}
