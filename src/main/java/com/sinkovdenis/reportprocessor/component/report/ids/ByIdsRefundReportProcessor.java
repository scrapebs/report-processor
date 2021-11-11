package com.sinkovdenis.reportprocessor.component.report.ids;

import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportprocessor.persistence.entity.RefundEntity;
import com.sinkovdenis.reportprocessor.persistence.repo.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ByIdsRefundReportProcessor implements ReportProcessor<ByIdsReportRequest> {

    private final RefundRepository repository;

    @Override
    public ReportType getReportType() {
        return ReportType.REFUNDS_REPORT;
    }

    @Override
    public Class<ByIdsReportRequest> getRequestClass() {
        return ByIdsReportRequest.class;
    }

    @Override
    public Report process(ByIdsReportRequest request) {
        List<RefundEntity> reportRows = repository.findByIdIn(request.getIds());
        return createReport(request, reportRows);
    }
}
