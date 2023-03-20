package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportProcessorTest {
    
    private ReportProcessor reportProcessor;
    
    @Mock
    private GenericReportRequest request;
    
    @BeforeEach
    public void setUp() {
        reportProcessor = Mockito.mock(ReportProcessor.class, Mockito.CALLS_REAL_METHODS);
    }
    
    @Test
    void testSupport_true() {
        when(reportProcessor.getRequestClass()).thenReturn(GenericReportRequest.class);
        when(reportProcessor.getReportType()).thenReturn(ReportType.ORDERS_REPORT);
        when(request.getReportType()).thenReturn(ReportType.ORDERS_REPORT);
        
        assertThat(reportProcessor.support(request)).isTrue();
    }

    @Test
    void testSupport_false() {
        when(reportProcessor.getRequestClass()).thenReturn(GenericReportRequest.class);
        when(reportProcessor.getReportType()).thenReturn(ReportType.ORDERS_REPORT);
        when(request.getReportType()).thenReturn(ReportType.REFUNDS_REPORT);

        assertThat(reportProcessor.support(request)).isFalse();
    }

    @Test
    void testCreateReport() {
        assertThat(reportProcessor.createReport(request, Collections.emptyList()))
                .isNotNull()
                .isInstanceOf(Report.class);
    }
}
