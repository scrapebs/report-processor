package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportProcessorTest {
    
    private ReportProcessor<GenericReportRequest> reportProcessor;
    
    @Mock
    private GenericReportRequest request;
    
    @Before
    public void setUp() {
        reportProcessor = Mockito.mock(ReportProcessor.class, Mockito.CALLS_REAL_METHODS);
    }
    
    @Test
    public void testSupport_true() {
        when(reportProcessor.getRequestClass()).thenReturn(GenericReportRequest.class);
        when(reportProcessor.getReportType()).thenReturn(ReportType.ORDERS_REPORT);
        when(request.getReportType()).thenReturn(ReportType.ORDERS_REPORT);
        
        assertThat(reportProcessor.support(request)).isTrue();
    }

    @Test
    public void testSupport_false() {
        when(reportProcessor.getRequestClass()).thenReturn(GenericReportRequest.class);
        when(reportProcessor.getReportType()).thenReturn(ReportType.ORDERS_REPORT);
        when(request.getReportType()).thenReturn(ReportType.REFUNDS_REPORT);

        assertThat(reportProcessor.support(request)).isFalse();
    }


    @Test
    public void testCreateReport() {
        assertThat(reportProcessor.createReport(request, Collections.emptyList()))
                .isNotNull()
                .isInstanceOf(Report.class);
    }
}
