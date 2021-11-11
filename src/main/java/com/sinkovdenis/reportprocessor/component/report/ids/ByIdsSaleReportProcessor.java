package com.sinkovdenis.reportprocessor.component.report.ids;

import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportprocessor.persistence.entity.SaleEntity;
import com.sinkovdenis.reportprocessor.persistence.repo.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ByIdsSaleReportProcessor implements ReportProcessor<ByIdsReportRequest> {

    private final SaleRepository repository;

    @Override
    public ReportType getReportType() {
        return ReportType.SALES_REPORT;
    }

    @Override
    public Class<ByIdsReportRequest> getRequestClass() {
        return ByIdsReportRequest.class;
    }

    @Override
    public Report process(ByIdsReportRequest request) {
        List<SaleEntity> reportRows = repository.findByIdIn(request.getIds());
        return createReport(request, reportRows);
    }
}
