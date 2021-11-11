package com.sinkovdenis.reportprocessor.service;

import com.sinkovdenis.reportprocessor.component.EmailSender;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessorResolver;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportProcessorService {

    private final ReportProcessorResolver reportProcessorResolver;
    private final EmailSender emailSender;

    public <R extends GenericReportRequest> void process(R request) {
        Optional<ReportProcessor<R>> reportProcessor = reportProcessorResolver.resolveBy(request);
        if (reportProcessor.isPresent()) {
            Report report = reportProcessor.get().process(request);
            emailSender.sendReport(report);
        } else {
            throw new IllegalArgumentException("Failed to find suitable processor for reportType" + request.getReportType());
        }
    }
}
