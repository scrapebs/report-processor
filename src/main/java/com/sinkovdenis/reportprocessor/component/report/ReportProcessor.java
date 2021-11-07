package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;

import java.util.Date;
import java.util.List;

public interface ReportProcessor<R extends GenericReportRequest> {

    ReportType getReportType();

    Class<R> getRequestClass();

    Report process(R request);

    default boolean support(GenericReportRequest request) {
        return getRequestClass().isInstance(request)
                && getReportType() == request.getReportType();
    }

    default Report createReport(GenericReportRequest request, List<?> reportRows) {
        return Report.builder()
                .reportRows(reportRows)
                .emailForResponse(request.getEmail())
                .creationDate(new Date())
                .build();
    }
}
