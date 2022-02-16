package com.sinkovdenis.reportprocessor.component.report.ids;

import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import com.sinkovdenis.reportprocessor.persistence.repo.RefundRepository;
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
public class ByIdsRefundReportProcessorTest {
    
    @Spy
    @InjectMocks
    private ByIdsRefundReportProcessor processor;
    
    @Mock
    private RefundRepository repository;
    
    @Mock
    private ByIdsReportRequest request;
    
    @Mock
    private Report report;
    
    @Test
    public void testGetReportType() {
        assertThat(processor.getReportType()).isEqualTo(ReportType.REFUNDS_REPORT);
    }

    @Test
    public void testGetRequestClass() {
        assertThat(processor.getRequestClass()).isEqualTo(ByIdsReportRequest.class);
    }

    @Test
    public void testProcess() {
        doReturn(report).when(processor).createReport(any(), anyList());
        assertThat(processor.process(request))
                .isNotNull()
                .isEqualTo(report);
        verify(repository).findByIdIn(anyList());
        verify(processor).createReport(any(), anyList());
    }
}
