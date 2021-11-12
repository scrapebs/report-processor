package com.sinkovdenis.reportprocessor.component.report.date;

import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.persistence.entity.OrderEntity;
import com.sinkovdenis.reportprocessor.persistence.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ByDateOrderReportProcessor implements ReportProcessor<ByDateReportRequest> {

    private final OrderRepository repository;

    @Override
    public ReportType getReportType() {
        return ReportType.ORDERS_REPORT;
    }

    @Override
    public Class getRequestClass() {
        return ByDateReportRequest.class;
    }

    public Report process(ByDateReportRequest request) {
        List<OrderEntity> reportRows = repository.findByCreationDateBetween(request.getFrom(), request.getTo());
        return createReport(request, reportRows);
    }
}
