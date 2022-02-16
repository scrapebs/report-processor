package com.sinkovdenis.reportprocessor.component.report.date;

import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.persistence.repo.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ByDateOrderReportProcessorTest {

    @Spy
    @InjectMocks
    private ByDateOrderReportProcessor processor;

    @Mock
    private OrderRepository repository;

    @Mock
    private ByDateReportRequest request;

    @Mock
    private Report report;

    @Test
    public void testGetReportType() {
        assertThat(processor.getReportType()).isEqualTo(ReportType.ORDERS_REPORT);
    }

    @Test
    public void testGetRequestClass() {
        assertThat(processor.getRequestClass()).isEqualTo(ByDateReportRequest.class);
    }

    @Test
    public void testProcess() {
        doReturn(report).when(processor).createReport(any(), anyList());
        assertThat(processor.process(request))
                .isNotNull()
                .isEqualTo(report);
        verify(repository).findByCreationDateBetween(any(), any());
        verify(processor).createReport(any(), anyList());
    }
}