package com.sinkovdenis.reportprocessor.component.report.ids;

import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportprocessor.persistence.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ByIdsOrderReportProcessorTest {

    @Spy
    @InjectMocks
    private ByIdsOrderReportProcessor processor;

    @Mock
    private OrderRepository repository;

    @Mock
    private ByIdsReportRequest request;

    @Mock
    private Report report;

    @Test
    void testGetReportType() {
        assertThat(processor.getReportType()).isEqualTo(ReportType.ORDERS_REPORT);
    }

    @Test
    void testGetRequestClass() {
        assertThat(processor.getRequestClass()).isEqualTo(ByIdsReportRequest.class);
    }

    @Test
    void testProcess() {
        doReturn(report).when(processor).createReport(any(), anyList());
        assertThat(processor.process(request))
                .isNotNull()
                .isEqualTo(report);
        verify(repository).findByIdIn(anyList());
        verify(processor).createReport(any(), anyList());
    }
}