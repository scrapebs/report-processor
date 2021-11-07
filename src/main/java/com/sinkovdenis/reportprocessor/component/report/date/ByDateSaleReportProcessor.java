package com.sinkovdenis.reportprocessor.component.report.date;

import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.persistence.entity.SaleEntity;
import com.sinkovdenis.reportprocessor.persistence.repo.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ByDateSaleReportProcessor implements ReportProcessor<ByDateReportRequest> {

    private final SaleRepository repository;

    @Override
    public ReportType getReportType() {
        return ReportType.SALES_REPORT;
    }

    @Override
    public Class getRequestClass() {
        return ByDateReportRequest.class;
    }

    public Report process(ByDateReportRequest request) {
        List<SaleEntity> reportRows = repository.findByCreationDateBetween(request.getFrom(), request.getTo());
        return createReport(request, reportRows);
    }
}
