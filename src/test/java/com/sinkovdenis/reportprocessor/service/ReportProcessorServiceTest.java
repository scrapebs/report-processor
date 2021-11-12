package com.sinkovdenis.reportprocessor.service;

import com.sinkovdenis.reportprocessor.component.EmailNotifier;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessorResolver;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateOrderReportProcessor;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportProcessorServiceTest {

    @Mock
    private ReportProcessorResolver reportProcessorResolver;

    @Mock
    private EmailNotifier emailNotifier;

    @Mock
    private ByDateReportRequest request;

    @Mock
    private ByDateOrderReportProcessor reportProcessor;

    @Mock
    private Report report;

    @InjectMocks
    private ReportProcessorService service;

    @Test
    public void testProcess() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.of(reportProcessor));
        when(reportProcessor.process(request)).thenReturn(report);
        service.process(request);
        verify(reportProcessorResolver).resolveBy(request);
        verify(reportProcessor).process(request);
        verify(emailNotifier).sendReport(report);
    }

    @Test
    public void testProcess_exception() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.empty());
        assertThat(catchThrowableOfType(() ->
                        service.process(request)
                , IllegalArgumentException.class
        )).isNotNull();
    }
}
