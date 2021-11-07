package com.sinkovdenis.reportprocessor.component.report.date;

import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.persistence.entity.RefundEntity;
import com.sinkovdenis.reportprocessor.persistence.entity.SaleEntity;
import com.sinkovdenis.reportprocessor.persistence.repo.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ByDateRefundReportProcessor implements ReportProcessor<ByDateReportRequest> {

    private final RefundRepository repository;

    @Override
    public ReportType getReportType() {
        return ReportType.REFUNDS_REPORT;
    }

    @Override
    public Class<ByDateReportRequest> getRequestClass() {
        return ByDateReportRequest.class;
    }

    public Report process(ByDateReportRequest request) {
        List<RefundEntity> reportRows = repository.findByCreationDateBetween(request.getFrom(), request.getTo());
        return createReport(request, reportRows);
    }
}
